package se.mirado.jgs.data.time;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javaslang.control.Try;

public class SimpleDate {

	private final int year;
	private final int month;
	private final int day;

	protected SimpleDate(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}

	public static SimpleDate fromDate(LocalDate date) {
		return new SimpleDate(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
	}

	public static Try<SimpleDate> fromStringDate(String strDate) {
		
		//TODO - LocalDate.parse can't parse single digit days - perhaps enforce this on the output :(
		System.out.println(strDate);
		Try<SimpleDate> t = Try.of( () -> fromDate(LocalDate.parse(strDate, DateTimeFormatter.ofPattern("yyyy-m-d"))));
		//LocalDate.parse(strDate, 
		System.out.println(t.isFailure());
		
		return t;
	}

	public static SimpleDate today() {
		return fromDate(LocalDate.now());
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

	@Override
	public String toString() {
		return year + "-" + month + "-" + day; 
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof SimpleDate) {
			SimpleDate s = (SimpleDate)other;
			return day == s.day
				&& month == s.month
				&& year == s.year;
		}
		return false;
	}

}
