package se.mirado.jgs.data.time;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import java.time.*;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;

public class CalFactory {

	private TemporalField dayOfWeek;

	public CalFactory(Locale locale) {
		this.dayOfWeek = WeekFields.of(locale).dayOfWeek();
	}

	public Mnth fromLocaleDate(LocalDate targetDate) {

		int targetYear = targetDate.getYear();
		int targetMonth = targetDate.getMonthValue();
		
		boolean isLeap = Year.isLeap(targetDate.getYear());

		int lastDayOfMonth =
				Month.from(targetDate).length(isLeap);

		LocalDate firstDateOfMonth =
				LocalDate.of(targetYear, targetMonth, 1);

		LocalDate lastDateOfMonth =
				LocalDate.of(targetYear, targetMonth, lastDayOfMonth);

		LocalDate firstDayToRender =
				firstDateOfMonth.with(dayOfWeek, 1);

		LocalDate lastDayToRender =
				lastDateOfMonth.with(dayOfWeek, 7);

		List<Wk> weeks = new ArrayList<Wk>();

		List<CalendarDate> days = new ArrayList<CalendarDate>();

		int dow = 0;
		for(LocalDate date = firstDayToRender; date.getDayOfYear() <= lastDayToRender.getDayOfYear(); date = date.plusDays(1)) {
			days.add(CalendarDate.fromDate(date.getMonthValue() == targetMonth, date));
			if(dow++ == 6) {
				dow = 0;
				weeks.add(Wk.fromDays(days));
				days = new ArrayList<CalendarDate>(); 
			}
		}

		return Mnth.fromWeeks( weeks);
	}

}
