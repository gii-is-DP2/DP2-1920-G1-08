package org.springframework.inmocasa.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Compra;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.service.CompraService;
import org.springframework.inmocasa.service.ViviendaService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.bytebuddy.asm.Advice.This;

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
//		Collection<Vivienda> vvnd = (Collection<Vivienda>) viviendaService.findAll();
//		List<Vivienda> todasViviendas = new ArrayList<Vivienda>(vvnd);
//		List<Vivienda> viviendas = new ArrayList<>();
//		Iterator<Compra> cmp =  compraService.findAll().iterator();
//		System.out.println("2jdf" + cmp);
//		System.out.println("tamano" + cmp.size());
//		List<Compra> todasOferta = new ArrayList<>(cmp);
//		todasOferta.addAll(cmp);
//		System.out.println("tamano" + todasOferta);
//		System.out.println("tamano" + todasOferta.size());
//
//		String username = SecurityContextHolder.getContext().getAuthentication().getName(); // username Actual
//
//		for (int i = 0; i < todasViviendas.size() ; i++) {
//			Vivienda v = todasViviendas.get(i);
//			if (v.getPropietario().getUsername().equals(username)) {
//				viviendas.add(v);
//				for (int c = 0; c < cmp.; c++) {
//					if (todasOferta.get(c).getVivienda().equals(v)) {
//						viviendas.add(v);
//					}
//				}
//			}

//		Iterable<Vivienda> vvnd =  viviendaService.findAll();
//        List<Vivienda> viviendas = new ArrayList<>();
//        String username = SecurityContextHolder.getContext().getAuthentication().getName(); // username Actual
//        System.out.println(username);
//
//        Iterable<Compra> cmp =  compraService.findAll();
//
//        for (Vivienda v : vvnd) {
//            if (v.getPropietario().getUsername().equals(username)) {
//                for (Compra compra : cmp) {
//                    if (compra.getVivienda().equals(v)) {
//                        viviendas.add(v);
//                    }
//                }
//
//            }

//		List<Vivienda> viviendas = new ArrayList<>();
//		String username = SecurityContextHolder.getContext().getAuthentication().getName(); // username Actual
//		Iterable<Compra> cmp = compraService.findAll();
//		for (Compra c : cmp) {
//			if (c.getVivienda().getPropietario().getUsername().equals(username)) {
//				viviendas.add(c.getVivienda());
//			}
//
//		}

		Collection<Vivienda> vvnd = (Collection<Vivienda>) viviendaService.findAll();
		List<Vivienda> todasViviendas = new ArrayList<Vivienda>(vvnd);
		List<Vivienda> viviendas = new ArrayList<>();

		String username = SecurityContextHolder.getContext().getAuthentication().getName(); // username Actual
		for (int i = 0; i < todasViviendas.size(); i++) {
			Vivienda v = todasViviendas.get(i);
			if (v.getPropietario().getUsername().equals(username)) {
				viviendas.add(v);
			}
		}

		model.addAttribute("viviendas", viviendas);

		return vista;
	}

	@GetMapping(value= "/{viviendaId}")
	public String showVivienda(@PathVariable("viviendaId") int viviendaId,ModelMap model) {
		Vivienda viviendas = this.viviendaService.findViviendaById(viviendaId);
		String view="viviendas/showViviendaDetails";
		model.put("viviendas",viviendas);
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
