package se.mirado.jgs.data.time;

import java.util.ArrayList;
import java.util.List;

public class CalRenderer {

	public static List<List<Dy>> render (Mnth month) {
		List<List<Dy>> renderedWeeks = new ArrayList<>();
		for (Wk week : month.getWeeks() ) {
			List<Dy> renderedDays = new ArrayList<>();
			for(Dy day : week.getDays() ) {
				renderedDays.add(day);
			}
			renderedWeeks.add(renderedDays);
		}
		return renderedWeeks;
	}

}
