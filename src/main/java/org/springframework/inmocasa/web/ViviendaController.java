package org.springframework.inmocasa.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Compra;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.model.enums.Estado;
import org.springframework.inmocasa.service.CompraService;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.inmocasa.service.ViviendaService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/viviendas")
public class ViviendaController {

	@Autowired
	private ViviendaService viviendaService;

	@Autowired
	private PropietarioService propService;

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
		Vivienda vivienda = this.viviendaService.findViviendaById(viviendaId);
		String view = "viviendas/showViviendaDetails";
		model.put("vivienda", vivienda);
		return view;

	}

	@GetMapping(path = "/mis-viviendas")
	public String misViviendas(ModelMap model) {
		String vista = "viviendas/misViviendas";
		List<Vivienda> viviendas = new ArrayList<>();
		String username = SecurityContextHolder.getContext().getAuthentication().getName(); // username Actual
		Iterable<Vivienda> cmp = viviendaService.findAll();
		for (Vivienda v : cmp) {
			if (v.getPropietario().getUsername().equals(username)) {

				viviendas.add(v);

			}

		}

		model.addAttribute("viviendas", viviendas);

		return vista;
	}

	@GetMapping(path = "/{viviendaId}/edit")
	public String editVivienda(@PathVariable("viviendaId") int viviendaId, ModelMap model) {
		Vivienda vivienda = this.viviendaService.findViviendaById(viviendaId);
		String view = "viviendas/editVivienda";
		model.addAttribute("vivienda", vivienda);
		return view;

	}

	@PostMapping(path = { "/{viviendaId}/save" })
	public String guardarPostActualizarVivienda(@PathVariable("viviendaId") int viviendaId, @Valid Vivienda vivienda,
			BindingResult result, ModelMap modelMap) {
		String view = "viviendas/listNewViviendas";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
		Propietario propietario = propService.findByUsername(userPrincipal.getUsername());
		vivienda.setPropietario(propietario);
		vivienda.setDenunciado(false);
		if (result.hasErrors()) {
			vivienda.setId(viviendaId);
			modelMap.addAttribute("vivienda", vivienda);
			return "viviendas/editVivienda";
		} else {
			viviendaService.save(vivienda);
			modelMap.addAttribute("message", "La vivienda ha sido registrada correctamente");
		}
		return view;
	}

	// Alvaro-MiguelEmmanuel
	@GetMapping(value = { "/allNew" })
	public String showListViviendas(Map<String, Object> model) {

		Collection<Vivienda> vivs = viviendaService.findAllNewest();
		model.put("viviendas", vivs);

		return "viviendas/listNewViviendas";
	}

	@GetMapping(value = { "/new" })
	public String crearVivienda(ModelMap modelMap) {
		String view = "viviendas/editVivienda";
		Vivienda vivienda = new Vivienda();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
		Propietario propietario = propService.findByUsername(userPrincipal.getUsername());
		vivienda.setPropietario(propietario);
		vivienda.setDenunciado(false);
		modelMap.addAttribute("vivienda", vivienda);
		return view;
	}

	@PostMapping(value= {"/save"})
	public String guardarVivienda(@Valid Vivienda vivienda, BindingResult result, ModelMap modelMap) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
		Propietario propietario = propService.findByUsername(userPrincipal.getUsername());
		vivienda.setPropietario(propietario);
		vivienda.setDenunciado(false);
		if(result.hasErrors()) {
			modelMap.addAttribute("vivienda", vivienda);
			return "viviendas/editVivienda";
		}else {
			viviendaService.save(vivienda);
			modelMap.addAttribute("message", "La vivienda ha sido registrada correctamente");
	     }
		return misViviendas(modelMap);
	}

	@GetMapping(value = "/{viviendaId}/denunciar")
	public String denunciarVivienda(@PathVariable("viviendaId") int viviendaId, ModelMap model) {
		Vivienda viviendas = this.viviendaService.ViviendaById(viviendaId);
		viviendas.setDenunciado(true);
		viviendaService.save(viviendas);
		model.addAttribute("viviendas", viviendas);
		model.addAttribute("message", "La vivienda ha sido denunciada correctamente");

		return showListViviendas(model);

	}

	@GetMapping(value = "/delete/{viviendaId}")
	public String borrarVivienda(@PathVariable("viviendaId") int viviendaId) {
		Vivienda vivienda = viviendaService.findViviendaById(viviendaId);
		viviendaService.delete(vivienda);

		return "viviendas/listaViviendasOferta";
	}

}
