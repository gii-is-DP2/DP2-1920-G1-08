package org.springframework.inmocasa.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Compra;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.model.enums.Estado;
import org.springframework.inmocasa.service.CompraService;
import org.springframework.inmocasa.service.ViviendaService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/viviendas")
public class ViviendaController {

	@Autowired
	private ViviendaService viviendaService;

	@Autowired
	private CompraService compraService;
	// Santi-Alvaro

	@GetMapping(path = "/ofertadas")
	public String listadoViviendas(ModelMap model) {
		String vista = "viviendas/listaViviendasOferta";
		List<Vivienda> viviendas = new ArrayList<>();
		String username = SecurityContextHolder.getContext().getAuthentication().getName(); // username Actual
		Iterable<Compra> cmp = compraService.findAll();
		for (Compra c : cmp) {
			if (c.getVivienda().getPropietario().getUsername().equals(username)) {
				if (c.getEstado().equals(Estado.PENDIENTE)) {
					viviendas.add(c.getVivienda());
				}
			}

		}

		model.addAttribute("viviendas", viviendas);

		return vista;
	}

	@GetMapping(value = "/{viviendaId}")
	public String showVivienda(@PathVariable("viviendaId") int viviendaId, ModelMap model) {
		Vivienda viviendas = this.viviendaService.findViviendaById(viviendaId).get();
		String view = "viviendas/showViviendaDetails";
		model.put("viviendas", viviendas);
		return view;

	}

	// Alvaro-MiguelEmmanuel
	@GetMapping(value = { "/allNew" })
	public String showListViviendas(Map<String, Object> model) {

		Collection<Vivienda> vivs = viviendaService.findAllNewest();
		model.put("viviendas", vivs);

		return "viviendas/listNewViviendas";
	}
	// Alba-Alejandro

}
