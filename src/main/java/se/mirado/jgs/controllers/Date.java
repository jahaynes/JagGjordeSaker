package se.mirado.jgs.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import se.mirado.jgs.data.time.SimpleDate;

@Controller
public class Date {

	private final Index index;

	public Date(@Autowired Index index) {
		this.index = index;
	}

	@GetMapping("/date/{strDate}")
	public String route(Model model, @PathVariable("strDate") String strDate) {
		
		SimpleDate date = SimpleDate
			.fromStringDate(strDate)
			.getOrElse(SimpleDate.today());
		
		return index.page(model, LocalDate.now(), date);

	}

}
