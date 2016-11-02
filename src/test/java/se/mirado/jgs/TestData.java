package se.mirado.jgs;

import se.mirado.jgs.data.HtmlEscaped;
import se.mirado.jgs.data.time.SimpleDate;

import java.time.LocalDate;

public class TestData {

	public static final HtmlEscaped alice = HtmlEscaped.make("Alice");

	public static final HtmlEscaped bob = HtmlEscaped.make("Bob");

	public static final HtmlEscaped done1 = HtmlEscaped.make("Did a thing");

	public static final SimpleDate date1 = SimpleDate.fromDate(LocalDate.of(2000, 4, 22));

}
