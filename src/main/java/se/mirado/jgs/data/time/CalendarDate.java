package se.mirado.jgs.data.time;

import java.time.LocalDate;

public class CalendarDate extends SimpleDate {

	private final boolean isPartOfMonth;

	private CalendarDate(boolean isPartOfMonth, int year, int month, int day) {
		super(year,month,day);
		this.isPartOfMonth = isPartOfMonth;
	}

	public static CalendarDate fromDate(boolean outlier, LocalDate date) {
		return new CalendarDate(outlier, date.getYear(), date.getMonthValue(), date.getDayOfMonth());
	}

	public boolean isPartOfMonth() {
		return isPartOfMonth;
	}

}
