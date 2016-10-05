package se.mirado.jgs.data;

public class Done {

	private final long id;
	private final HtmlEscaped consultantName;
	private final HtmlEscaped consultantDone;

	private Done(long id, HtmlEscaped consultantName, HtmlEscaped consultantDone) {
		this.id = id;
		this.consultantName = consultantName;
		this.consultantDone = consultantDone;
	}

	public static Done make(long id, HtmlEscaped consultantName, HtmlEscaped consultantDone) {
		return new Done(id, consultantName, consultantDone);
	}

	public Long getId() {
		return id;
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

		if ( ! (other instanceof Done)) {
			return false;
		}

		Done done = (Done)other;

		return done.consultantName.equals(consultantName)
			&& done.consultantDone.equals(consultantDone);

	}
	
}
