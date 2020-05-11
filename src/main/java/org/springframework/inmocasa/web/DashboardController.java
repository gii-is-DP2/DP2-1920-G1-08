package org.springframework.inmocasa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.service.CompraService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	CompraService compraService;
	
	@Autowired
	public DashboardController(CompraService compraService) {
		this.compraService = compraService;
	}
	
	@GetMapping
	public ModelAndView dashboard() {
		ModelAndView res = new ModelAndView("dashboard/show");
		Integer numOfertas = compraService.getNumOfertas();
		Integer viviendasCompradas = compraService.getViviendasCompradas();
		res.addObject("numOfertas", numOfertas);
		res.addObject("viviendasCompradas", viviendasCompradas);
		return res;
	}
}
