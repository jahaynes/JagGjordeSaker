package se.mirado.jgs.data.time;

import javaslang.control.Try;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@EqualsAndHashCode
@AllArgsConstructor (access = AccessLevel.PROTECTED)
public class SimpleDate {

	private final int year;
	private final int month;
	private final int day;

	public static SimpleDate fromDate(LocalDate date) {
		return new SimpleDate(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
	}

	public static Try<SimpleDate> fromStringDate(String strDate) {
		return Try.of( () -> fromDate(LocalDate.parse(strDate)));
	}

	public static SimpleDate today() {
		return fromDate(LocalDate.now());
	}

	@Override
	public String toString() {

		return year + "-"
			+ String.format("%02d", month) + "-"
			+ String.format("%02d", day); 

	}

}
