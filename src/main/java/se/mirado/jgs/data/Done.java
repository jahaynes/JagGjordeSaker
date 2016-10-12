package se.mirado.jgs.data;

import se.mirado.jgs.data.time.SimpleDate;

public class Done {

	private final long id;
	private final SimpleDate date;
	private final HtmlEscaped consultantName;
	private final HtmlEscaped consultantDone;

	private Done(long id, SimpleDate date, HtmlEscaped consultantName, HtmlEscaped consultantDone) {
		this.id = id;
		this.date = date;
		this.consultantName = consultantName;
		this.consultantDone = consultantDone;
	}

	public static Done make(long id, SimpleDate date, HtmlEscaped consultantName, HtmlEscaped consultantDone) {
		return new Done(id, date, consultantName, consultantDone);
	}

	public Long getId() {
		return id;
	}
	
	public SimpleDate getDate() {
		return date;
	}

	public HtmlEscaped getConsultantName() {
		return consultantName;
	}

	public HtmlEscaped getConsultantDone() {
		return consultantDone;
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
