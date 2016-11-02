package se.mirado.jgs.data;

import javaslang.collection.SortedMap;
import javaslang.collection.TreeMap;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AppState {

	private final long nextEventId;
	private final SortedMap<Long, Done> dones;

	public AppState() {
		nextEventId = 1;
		dones = TreeMap.empty();
	}

	private AppState(long nextEventId, SortedMap<Long, Done> dones) {
		this.nextEventId = nextEventId;
		this.dones = dones;
	}

	public AppState prepend(Function<Long, Done> addDoneFunc) {		
		return new AppState(nextEventId+1, dones.put(nextEventId, addDoneFunc.apply(nextEventId)));
	}

	public AppState remove(Long eventId) {
		return new AppState(nextEventId+1, dones.remove(eventId));
	}

	@Override
	public boolean equals(Object other) {

		//Ignore nextEventId

		return other instanceof AppState
			&& ((AppState)other).dones.equals(dones);

	}

}
