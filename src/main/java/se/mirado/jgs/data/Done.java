package se.mirado.jgs.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import se.mirado.jgs.data.time.SimpleDate;

@Getter
@AllArgsConstructor (access = AccessLevel.PRIVATE)
public class Done {

	private final long id;
	private final SimpleDate date;
	private final HtmlEscaped consultantName;
	private final HtmlEscaped consultantDone;

	public static Done make(long id, SimpleDate date, HtmlEscaped consultantName, HtmlEscaped consultantDone) {
		return new Done(id, date, consultantName, consultantDone);
	}

	@Override
	public boolean equals(Object other) {

		//Ignore id

		//Date tbd...

		if ( ! (other instanceof Done)) {
			return false;
		}

		Done done = (Done)other;

		return done.consultantName.equals(consultantName)
			&& done.consultantDone.equals(consultantDone);

	}
	
}
