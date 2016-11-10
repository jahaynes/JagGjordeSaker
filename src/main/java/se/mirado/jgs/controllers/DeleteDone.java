package se.mirado.jgs.controllers;

import javaslang.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.mirado.jgs.AppReactor;
import se.mirado.jgs.Security;
import se.mirado.jgs.common.Permission;
import se.mirado.jgs.common.Update;
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

	public static Update run(HtmlEscaped userName, long doneId) {

		return Update.named(
		        "Deleting a done id " + doneId + " for " + userName.escapedString,
		        as -> {
		            Permission permission =
	                    as.getPermission(userName, doneId);
		            return permission == Permission.HasPermission
	                    ? Try.of( () -> as.remove(doneId) )
                        : Try.failure(new RuntimeException("Could not delete " + doneId + ": " + permission));
		});
	}

}
