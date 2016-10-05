package se.mirado.jgs.controllers;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import se.mirado.jgs.AppReactor;
import se.mirado.jgs.Security;
import se.mirado.jgs.data.AppState;
import se.mirado.jgs.data.Done;
import se.mirado.jgs.data.HtmlEscaped;

@Controller
public class AddDone {

	private final AppReactor appReactor;

	@Autowired
	public AddDone(AppReactor appReactor) {
		this.appReactor = appReactor;
	}

	@PostMapping("/addDone")
	public String index(@RequestParam(value="consultantDone", required=true) String consultantDone) {
		appReactor.update( run(Security.getEscapedUserName(), HtmlEscaped.make(consultantDone) ));
		return "/";
	}

	public static Function<AppState,AppState> run(HtmlEscaped loggedInName, HtmlEscaped consultantDone) {
		return as -> as.prepend(
			id -> Done.make(id, loggedInName, consultantDone) );
	}

}
