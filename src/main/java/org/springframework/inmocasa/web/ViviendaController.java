package org.springframework.inmocasa.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.service.ViviendaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/vivienda")
public class ViviendaController {
	
	
	ViviendaService viviendaService;
	
	@Autowired
	public ViviendaController(ViviendaService viviendaService) {
		this.viviendaService = viviendaService;
	}

	
	//Santi-Alvaro
	
	
	//Alvaro-MiguelEmmanuel
	
	
	//Alba-Alejandro
	@GetMapping(value="/list")
	public ModelAndView list() {
		ModelAndView res;
		
		Collection<Vivienda> viviendas = viviendaService.findAllNewest();
		
		res = new ModelAndView("vivienda/list");
		res.addObject("viviendas", viviendas);
		
		return res;
	}
	
//	@GetMapping(value="/{viviendaId}")
//	public ModelAndView show(Integer viviendaId) {
//		ModelAndView res = new ModelAndView("/vivienda/show");
//		Vivienda 
//		res.addObject(vivienda);
//	}
	
}
