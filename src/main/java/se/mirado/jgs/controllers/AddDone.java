package se.mirado.jgs.controllers;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javaslang.control.Try;
import se.mirado.jgs.AppReactor;
import se.mirado.jgs.Security;
import se.mirado.jgs.data.AppState;
import se.mirado.jgs.data.Done;
import se.mirado.jgs.data.HtmlEscaped;
import se.mirado.jgs.data.time.SimpleDate;

@Controller
public class AddDone {

	private final AppReactor appReactor;

	@Autowired
	public AddDone(AppReactor appReactor) {
		this.appReactor = appReactor;
	}

	@PostMapping("/addDone")
	public String addDone(
			@RequestParam(value="consultantDone", required=true) String consultantDone,
			@RequestParam(value="date", required=false) String strDate) {

		Try<SimpleDate> date =
				SimpleDate.fromStringDate(strDate);

		appReactor.update(
				run(date.getOrElse(SimpleDate.today()), Security.getEscapedUserName(), HtmlEscaped.make(consultantDone))
		);

		String redirection = date
				.map( d -> "/date/" + d.toString() )
				.getOrElse("/");

		return "redirect:" + redirection;
	}

	public static Function<AppState,AppState> run(SimpleDate date, HtmlEscaped loggedInName, HtmlEscaped consultantDone) {
		return as -> as.prepend(
			id -> Done.make(id, date, loggedInName, consultantDone) );
	}

}
