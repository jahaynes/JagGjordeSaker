package se.mirado.jgs.data;

import org.apache.commons.lang3.StringEscapeUtils;

public class HtmlEscaped {

	public final String escapedString;

	private HtmlEscaped(String escapedString) {
		this.escapedString = escapedString;
	}

	public static HtmlEscaped make(String str) {
		return new HtmlEscaped(StringEscapeUtils.escapeHtml4(str));
	}

	@Override
	public boolean equals(Object other) {
		return (other instanceof HtmlEscaped
				&& ((HtmlEscaped)other).escapedString.equals(escapedString));
	}

	@Override
	public int hashCode() {
		return escapedString.hashCode();
	}

	@Override
	public String toString() {
		return escapedString;
	}

}
