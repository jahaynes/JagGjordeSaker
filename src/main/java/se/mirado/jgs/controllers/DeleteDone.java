package se.mirado.jgs.controllers;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import se.mirado.jgs.AppReactor;
import se.mirado.jgs.Security;
import se.mirado.jgs.data.AppState;
import se.mirado.jgs.data.HtmlEscaped;

@Controller
public class DeleteDone {

	private final AppReactor appReactor;

	@Autowired
	public DeleteDone(AppReactor appReactor) {
		this.appReactor = appReactor;
	}

	@PostMapping("/deleteDone/{doneId}")
	public String index(@PathVariable long doneId) {
		appReactor.update( run(Security.getEscapedUserName(), doneId) );
		return "/";
	}

	public static Function<AppState,AppState> run(HtmlEscaped userName, long doneId) {

		return appState -> {

			appState
				.dones
				.get(doneId)
				.filter( done -> done.getConsultantName().equals(userName) )
				.getOrElseThrow( () -> new SecurityException(userName + " does not have permissions to delete id " + doneId) );

			return appState.remove(doneId);

		};
	}

}
