package se.mirado.jgs;

import java.time.LocalDate;

import se.mirado.jgs.data.HtmlEscaped;
import se.mirado.jgs.data.time.SimpleDate;

public class TestData {

	public static final HtmlEscaped alice = HtmlEscaped.make("Alice");

	public static final HtmlEscaped bob = HtmlEscaped.make("Bob");

	public static final HtmlEscaped done1 = HtmlEscaped.make("Did a thing");

	public static final SimpleDate date1 = SimpleDate.fromDate(LocalDate.of(2000, 04, 22));

}
