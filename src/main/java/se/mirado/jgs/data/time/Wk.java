package se.mirado.jgs.data.time;

import java.util.List;

public class Wk {

	private final List<Dy> days;

	private Wk(List<Dy> days) {
		this.days = days;
	}

	public static Wk fromDays(List<Dy> days) {
		return new Wk(days);
	}

	public List<Dy> getDays() {
		return days;
	}

}
