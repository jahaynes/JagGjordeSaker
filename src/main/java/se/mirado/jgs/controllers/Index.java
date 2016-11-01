package se.mirado.jgs.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import se.mirado.jgs.AppReactor;
import se.mirado.jgs.Security;
import se.mirado.jgs.common.Query;
import se.mirado.jgs.data.Done;
import se.mirado.jgs.data.time.CalFactory;
import se.mirado.jgs.data.time.CalRenderer;
import se.mirado.jgs.data.time.SimpleDate;

@Component
@Controller
public class Index {

	private final AppReactor appReactor;
	private final CalFactory calFactory;

	@Autowired
	public Index(AppReactor appReactor) {
		this.appReactor = appReactor;
		this.calFactory = new CalFactory(Locale.getDefault());
	}

	@RequestMapping("/")
	public String index(Model model) {
		return page(model, LocalDate.now(), SimpleDate.today());
	}

	public String page(Model model, LocalDate currentDate, SimpleDate targetDate) {

		List<Done> dones = appReactor
			.read( readFunc(targetDate) )
			.get();

		model.addAttribute("date", targetDate);
		model.addAttribute("username", Security.getEscapedUserName());
		model.addAttribute("dones", dones);
		model.addAttribute("calendar", CalRenderer.render(calFactory.fromLocaleDate(currentDate)));

		return "index";

	}
	
	private static Query<List<Done>> readFunc(SimpleDate targetDate) {
	    return Query.named(
	            "Reading dones for date " + targetDate.toString(),
	            as -> as.dones.filter(d -> d._2().getDate().equals(targetDate)).values().toJavaList());
	}

}
