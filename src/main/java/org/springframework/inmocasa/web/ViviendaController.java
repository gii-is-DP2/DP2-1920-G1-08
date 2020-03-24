package org.springframework.inmocasa.web;

import java.util.Collection;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.service.ViviendaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
	
	//List Vivienda por rango de precio
//	@GetMapping(value="/list")
//	public ModelAndView list(Integer preciomin, Integer preciomax) {
//		ModelAndView res = new ModelAndView("vivienda/list");
//		Collection<Vivienda> viviendas = viviendaService.findViviendaByPrecio(preciomin, preciomax);
//		res.addObject("viviendas", viviendas);
//		
//		return res;
//	}
	
	@GetMapping(value="/{viviendaId}")
	public ModelAndView show(@PathVariable("viviendaId") Integer viviendaId) {
		ModelAndView res = new ModelAndView("/vivienda/show");
		Vivienda vivienda = viviendaService.findViviendaById(viviendaId);
		res.addObject("vivienda", vivienda);
		
		return res;
	}
	
	//Delete Vivienda
	@GetMapping(value="/delete/{viviendaId}")
	public ModelAndView borrarVivienda(@PathVariable("viviendaId") int viviendaId) {
		ModelAndView res;
		Vivienda vivienda=viviendaService.findViviendaById(viviendaId);
		viviendaService.delete(vivienda);
		
		res = list();
				
		return res;
	}
	
}
