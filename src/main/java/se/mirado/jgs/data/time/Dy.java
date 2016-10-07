package se.mirado.jgs.data.time;

import java.time.LocalDate;

public class Dy {

	private final boolean isPartOfMonth;
	
	private final int year;
	private final int month;
	private final int day;

	private Dy(boolean isPartOfMonth, int year, int month, int day) {
		this.isPartOfMonth = isPartOfMonth;
		this.year = year;
		this.month = month;
		this.day = day;
	}

	public static Dy fromDate(boolean outlier, LocalDate date) {
		return new Dy(outlier, date.getYear(), date.getMonthValue(), date.getDayOfMonth());
	}

	public boolean isPartOfMonth() {
		return isPartOfMonth;
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

}
