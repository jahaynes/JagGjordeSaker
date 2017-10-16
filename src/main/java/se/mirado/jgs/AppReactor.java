package se.mirado.jgs;

import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.mirado.jgs.common.Query;
import se.mirado.jgs.common.Update;
import se.mirado.jgs.data.AppState;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class AppReactor extends Thread {

	private AppState appState;

	private final BlockingQueue<Update> queue; 

	@Autowired
	public AppReactor(AppState appState) {
		this.appState = appState;
		this.queue = new LinkedBlockingQueue<>();
	}

	/** Read from the AppState using a function */
	public <T> Try<T> query(Query<T> queryCommand) {
	    BlockingQueue<Try<T>> mvar = new LinkedBlockingQueue<>();
        queue.add(promote(queryCommand, mvar));
        return Try
                .success(mvar)
                .flatMapTry( m -> m.take() );
    }

	/**
	 * Promotes a query up to an update function
	 * so it can be put into the reactor
	 *
	 * @param queryCommand  A Query to promote
	 * @param mvar  A blocking container that others can poll for the answer
	 * @return  The Query promoted to an Update
	 */
private static <T> Update promote(final Query<T> queryCommand, BlockingQueue<Try<T>> mvar) {

    return Update.create(

        queryCommand.getMetric(),

        as -> {
            //The caller's query may or may not work
            Try<T> queryResult = queryCommand.apply(as);

            try {
                //Either way, send the result back to the caller
                //So they can handle their failure
                mvar.put(queryResult);
                
                return Try.success(as);
            } catch (InterruptedException e) {
                
                //This is our failure, not the caller's.
                return Try.failure(e);
            }
        });
	}

	/** Update the AppState using a function */
	public void update(Update updateCommand) {
        // TODO Send a queue-size metric here
		queue.add(updateCommand);
	}

	@Override
	public void run() {
		while(true) {
			try {
			    Update update = queue.take();
			    System.out.println(update.getMetric());

			    update
			        .apply( appState )
			        .map( updated -> this.appState = updated )
			        .orElseRun( e -> logError(e) );

			} catch (InterruptedException e) {
				System.err.println("Waiting on queue was interrupted");
				e.printStackTrace();
			}
		}
	}

	private static void logError(Throwable t) {
	    t.printStackTrace();
	}

}
