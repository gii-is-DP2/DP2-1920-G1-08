package org.springframework.inmocasa.web;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.service.ViviendaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		
		Collection<Vivienda> viviendas = viviendaService.findViviendasALaVenta();
		
		res = new ModelAndView("vivienda/list");
		res.addObject("viviendas", viviendas);
		
		return res;
	}
	
	@GetMapping(value="/{viviendaId}")
	public ModelAndView show(@PathVariable("viviendaId") Integer viviendaId) {
		ModelAndView res = new ModelAndView("/vivienda/show");
		Vivienda vivienda = viviendaService.findViviendaById(viviendaId);
		res.addObject("vivienda", vivienda);
		return res;
	}
	
}
