package org.springframework.inmocasa.web;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.service.ViviendaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value="viviendas")
@Controller
public class ViviendaController {

	private final ViviendaService vivService;
	
	@Autowired
	public ViviendaController (ViviendaService vivService) {
		this.vivService = vivService;
	}
	
	//Santi-Alvaro
	
	
	//Alvaro-MiguelEmmanuel
	@GetMapping(value = {"/allNew"})
	public String showListViviendas(Map<String, Object> model) {
		
		Collection<Vivienda> vivs = vivService.findAllNewest();
		model.put("viviendas", vivs);
		
		return "viviendas/listNewViviendas";
	}
	
	//Alba-Alejandro
	
	
}
