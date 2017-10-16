package se.mirado.jgs.data;

import io.vavr.test.Arbitrary;
import io.vavr.test.Gen;
import io.vavr.test.Property;
import org.junit.Assert;
import org.junit.Test;

public class HtmlEscapedTests {

	private final Arbitrary<String> strings =
			Arbitrary.string( Gen.choose(Character.MIN_VALUE, Character.MAX_VALUE));

	@Test
	public void testStripHtml() {
		String escaped = 
			HtmlEscaped
				.make("<html>Hello</html>")
				.escapedString;
		Assert.assertFalse(escaped.contains("<"));
		Assert.assertFalse(escaped.contains(">"));
	}

	@Test
	public void testEquality() {
		Property
			.def("An escaped string should equal itself")
			.forAll(strings)
			.suchThat( x -> HtmlEscaped.make(x).equals(HtmlEscaped.make(x)) )
			.check();
	}

	@Test
	public void testInequality() {
		Property
			.def("Different strings should map to different escaped strings")		
			.forAll(strings, strings)
			.suchThat( (x,y) -> x.equals(y) || ! HtmlEscaped.make(x).equals(HtmlEscaped.make(y)) )
			.check();
	}

}
