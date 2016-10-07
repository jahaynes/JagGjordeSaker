package se.mirado.jgs.data.time;

import java.util.List;

public class Mnth {

	private final List<Wk> weeks;

	private Mnth(List<Wk> weeks) {
		this.weeks = weeks;
	}

	public static Mnth fromWeeks(List<Wk> weeks) {
		return new Mnth(weeks);
	}

	public List<Wk> getWeeks() {
		return weeks;
	}

}
