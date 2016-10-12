package se.mirado.jgs.controllers;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javaslang.control.Try;
import se.mirado.jgs.AppReactor;
import se.mirado.jgs.Security;
import se.mirado.jgs.data.AppState;
import se.mirado.jgs.data.HtmlEscaped;
import se.mirado.jgs.data.time.SimpleDate;

@Controller
public class DeleteDone {

	private final AppReactor appReactor;

	@Autowired
	public DeleteDone(AppReactor appReactor) {
		this.appReactor = appReactor;
	}

	@PostMapping("/deleteDone/{doneId}")
	public String index(@PathVariable long doneId,
						@RequestParam(value="date", required=false) String strDate) {

		Try<SimpleDate> date =
				SimpleDate.fromStringDate(strDate);
		
		appReactor.update( run(Security.getEscapedUserName(), doneId) );

		String redirection = date
				.map( d -> "/date/" + d.toString() )
				.getOrElse("/");

		return "redirect:" + redirection;
	}

	public static Function<AppState,AppState> run(HtmlEscaped userName, long doneId) {

		return appState -> {

			try {
				appState
					.dones
					.get(doneId)
					.filter( done -> done.getConsultantName().equals(userName) )
					.getOrElseThrow( () -> new SecurityException(userName + " does not have permissions to delete id " + doneId) );

				return appState.remove(doneId);

			} catch (Exception e) {
				e.printStackTrace();
				return appState;
			}

		};
	}

}
