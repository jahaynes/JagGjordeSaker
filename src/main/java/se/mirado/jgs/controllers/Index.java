package se.mirado.jgs.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import se.mirado.jgs.AppReactor;
import se.mirado.jgs.Security;
import se.mirado.jgs.data.Done;

@Controller
public class Index {

	private final AppReactor appReactor;

	@Autowired
	public Index(AppReactor appReactor) {
		this.appReactor = appReactor;
	}

	@RequestMapping("/")
	public String index(Model model) throws InterruptedException {

		List<Done> dones = appReactor
			.read( as -> as.dones.take(10).values().toJavaList() )
			.get();

		model.addAttribute("username", Security.getEscapedUserName());
		model.addAttribute("dones", dones);

		return "index";
	}

}
