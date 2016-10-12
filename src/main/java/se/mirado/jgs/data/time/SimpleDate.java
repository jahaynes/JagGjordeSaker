package se.mirado.jgs.data.time;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javaslang.control.Try;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

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

	@Override
	public String toString() {
		return year + "-" + month + "-" + day; 
	}

}
