package se.mirado.jgs;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javaslang.control.Try;
import se.mirado.jgs.data.AppState;

@Component
public class AppReactor extends Thread {

	private AppState appState;

	private final BlockingQueue<Function<AppState, AppState>> queue; 

	@Autowired
	public AppReactor(AppState appState) {
		this.appState = appState;
		this.queue = new LinkedBlockingQueue<Function<AppState, AppState>>();
	}

	/** Read from the AppState using a function */
	public <X> Try<X> read(Function<AppState,X> query) {
		BlockingQueue<X> single = new LinkedBlockingQueue<X>();
		queue.add( as -> {
			try {
				single.put( query.apply(as) );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			return appState;
		});
		return Try.of( () -> single.take() );
	}

	/** Update the AppState using a function */
	public void update(Function<AppState, AppState> command) {
		queue.add(command);
	}

	@Override
	public void run() {
		while(true) {
			try {
				appState = queue.take().apply(appState);
			} catch (InterruptedException e) {
				System.err.println("Waiting on queue was interrupted");
				e.printStackTrace();
			}
		}
	}

}
