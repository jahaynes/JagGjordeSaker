package se.mirado.jgs.data;

import io.vavr.collection.SortedMap;
import io.vavr.collection.TreeMap;
import lombok.Getter;
import se.mirado.jgs.common.Permission;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AppState {

	private final long nextEventId;

	@Getter
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

	public Permission getPermission(HtmlEscaped userName, long doneId) {
        return dones
            .get(doneId)
            .map( done -> done.getConsultantName() )
            .map( name -> name.equals(userName)
                        ? Permission.HasPermission
                        : Permission.NoPermission )
            .getOrElse( Permission.ItemNotPresent );
    }

}
