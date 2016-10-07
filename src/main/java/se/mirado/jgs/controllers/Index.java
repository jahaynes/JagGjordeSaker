package se.mirado.jgs.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import se.mirado.jgs.AppReactor;
import se.mirado.jgs.Security;
import se.mirado.jgs.data.Done;
import se.mirado.jgs.data.time.CalFactory;
import se.mirado.jgs.data.time.CalRenderer;
import se.mirado.jgs.data.time.Dy;

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

		List<Done> dones = appReactor
			.read( as -> as.dones.take(10).values().toJavaList() )
			.get();

		model.addAttribute("username", Security.getEscapedUserName());
		model.addAttribute("dones", dones);
		model.addAttribute("calendar", renderCalendar(LocalDate.now()));

		return "index";
	}

	private List<List<Dy>> renderCalendar(LocalDate targetDate) {
		return CalRenderer.render(calFactory.fromLocaleDate(targetDate));
	}

}
