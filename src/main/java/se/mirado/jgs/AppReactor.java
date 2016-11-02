package se.mirado.jgs;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javaslang.control.Try;
import se.mirado.jgs.common.Query;
import se.mirado.jgs.common.Update;
import se.mirado.jgs.data.AppState;

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
	    BlockingQueue<T> mvar = new LinkedBlockingQueue<>();
        queue.add(promote(queryCommand, mvar));
        return Try.of(mvar::take);
    }

	/**
	 * Promotes a query up to an update function
	 * so it can be put into the reactor
	 *
	 * @param queryCommand  A Query to promote
	 * @param mvar  A blocking container that others can poll for the answer
	 * @return  The Query promoted to an Update
	 */
	private static <T> Update promote(final Query<T> queryCommand, BlockingQueue<T> mvar) {
	    return Update.create(
                queryCommand.getMetric(),
                as -> {
                    try {
                        mvar.put(queryCommand.apply(as));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return as;
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
				appState = update.apply(appState);
			} catch (InterruptedException e) {
				System.err.println("Waiting on queue was interrupted");
				e.printStackTrace();
			}
		}
	}

}
