package se.mirado.jgs.data.time;

import java.util.ArrayList;
import java.util.List;

public class CalRenderer {

	public static List<List<CalendarDate>> render (Mnth month) {
		List<List<CalendarDate>> renderedWeeks = new ArrayList<>();
		for (Wk week : month.getWeeks() ) {
			List<CalendarDate> renderedDays = new ArrayList<>();
            renderedDays.addAll(week.getDays());
            renderedWeeks.add(renderedDays);
		}
		return renderedWeeks;
	}

}
