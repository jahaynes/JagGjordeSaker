package se.mirado.jgs.data;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import javaslang.collection.SortedMap;
import javaslang.collection.TreeMap;

@Component
public class AppState {

	public final long nextEventId;
	public final SortedMap<Long, Done> dones;

	public AppState() {
		nextEventId = 1;
		dones = TreeMap.empty();
	}

	public AppState(long nextEventId, SortedMap<Long, Done> dones) {
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
