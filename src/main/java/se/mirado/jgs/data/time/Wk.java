package se.mirado.jgs.data.time;

import java.util.List;

public class Wk {

	private final List<CalendarDate> days;

	private Wk(List<CalendarDate> days) {
		this.days = days;
	}

	public static Wk fromDays(List<CalendarDate> days) {
		return new Wk(days);
	}

	public List<CalendarDate> getDays() {
		return days;
	}

}
