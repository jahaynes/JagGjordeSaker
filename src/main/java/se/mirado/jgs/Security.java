package se.mirado.jgs;

import org.springframework.security.core.context.SecurityContextHolder;

import se.mirado.jgs.data.HtmlEscaped;

public class Security {

	public static HtmlEscaped getEscapedUserName() {
		return HtmlEscaped.make(getLoggedInUsername());
	}

	private static String getLoggedInUsername() {
		return SecurityContextHolder
			.getContext()
			.getAuthentication()
			.getName();
	}

}
