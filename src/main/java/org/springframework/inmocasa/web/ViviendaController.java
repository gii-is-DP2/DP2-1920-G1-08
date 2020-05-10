package org.springframework.inmocasa.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Administrador;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Compra;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.model.enums.Estado;
import org.springframework.inmocasa.service.AdministradorService;
import org.springframework.inmocasa.service.ClienteService;
import org.springframework.inmocasa.service.CompraService;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.inmocasa.service.ViviendaService;
import org.springframework.lang.Nullable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/viviendas")
public class ViviendaController {

	@Autowired
	private ViviendaService viviendaService;

	@Autowired
	private AdministradorService adminService;

	@Autowired
	private PropietarioService propService;

	@Autowired
	private ClienteService clienteService;

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
				viviendas.add(c.getVivienda());

			}

		}

		model.addAttribute("viviendas", viviendas);

		return vista;
	}

	@GetMapping("/{viviendaId}")
	public String showVivienda(@PathVariable("viviendaId") int viviendaId,ModelMap model) {
		String view = "viviendas/showViviendaDetails";
		Vivienda vivienda = this.viviendaService.findViviendaById(viviendaId).get();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Cliente cliente = clienteService.findByUsername(username);
		if (cliente != null) {
			if (clienteService.esFavorito(cliente.getFavoritas(), vivienda.getId())) {
				vivienda.setFav(true);
			} else {
				vivienda.setFav(false);
			}
		}
		model.addAttribute("vivienda", vivienda);
		return view;
	}
//	@GetMapping(value = "/{viviendaId}")
//	public String showVivienda(@PathVariable("viviendaId") int viviendaId, ModelMap model) {
//		Vivienda vivienda = this.viviendaService.findViviendaById(viviendaId).get();
//		String username = SecurityContextHolder.getContext().getAuthentication().getName();
//		Propietario propietario = propService.findByUsername(username);
//		Cliente cliente = clienteService.findByUsername(username);
//		if (cliente != null) {
//			if (clienteService.esFavorito(cliente.getFavoritas(), vivienda.getId())) {
//				vivienda.setFav(true);
//			} else {
//				vivienda.setFav(false);
//			}
//		}
//
//		String view = "viviendas/showViviendaDetails";
//		model.put("vivienda", vivienda);
//		model.put("propietario", propietario);
//		return view;
//
//	}

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
		Vivienda vivienda = this.viviendaService.findViviendaById(viviendaId).get();
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
	public String showListViviendas(ModelMap model, @Nullable @RequestParam("precioMin") String precioMin,
			@Nullable @RequestParam("precioMax") String precioMax) {
		if (precioMin == null && precioMax == null) {
			Collection<Vivienda> vivs = viviendaService.findAllNewest();
			model.put("viviendas", vivs);
		} else if (precioMin != null && precioMax != null) {
			// Filtrar viviendas por precio
			Integer min = Integer.valueOf(precioMin);
			Integer max = Integer.valueOf(precioMax);
			Collection<Vivienda> viviendasPrecio = viviendaService.findViviendaByPrecio(min, max);
			if (viviendasPrecio.isEmpty()) {
				model.addAttribute("error", "No se han encontrado viviendas en este rango de precio");
			}
			model.put("viviendas", viviendasPrecio);
		}

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
		modelMap.addAttribute("vivienda", vivienda);
		return view;
	}

	@PostMapping(value = { "/save" })
	public String guardarVivienda(@Valid Vivienda vivienda, BindingResult result, ModelMap modelMap) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
		Propietario propietario = propService.findByUsername(userPrincipal.getUsername());
		vivienda.setPropietario(propietario);
		if (result.hasErrors()) {
			modelMap.addAttribute("vivienda", vivienda);
			return "viviendas/editVivienda";
		} else {
			viviendaService.save(vivienda);
			modelMap.addAttribute("message", "La vivienda ha sido registrada correctamente");
		}
		return misViviendas(modelMap);
	}

	@GetMapping(value = "/{viviendaId}/denunciar")
	public String denunciarVivienda(@PathVariable("viviendaId") int viviendaId, ModelMap model) {
		Vivienda viviendas = this.viviendaService.findViviendaById(viviendaId).orElse(null);
		viviendaService.save(viviendas);
		model.addAttribute("viviendas", viviendas);
		model.addAttribute("message", "La vivienda ha sido denunciada correctamente");

		return showListViviendas(model, null, null);
	}
	// Alba-Alejandro

	@GetMapping(value = "/delete/{viviendaId}")
	public String borrarVivienda(ModelMap model, @PathVariable("viviendaId") int viviendaId) {
		Vivienda vivienda = viviendaService.findViviendaId(viviendaId);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
		Propietario propietario = propService.findByUsername(userPrincipal.getUsername());

		Administrador admin = adminService.findAdministradorByUsername(userPrincipal.getUsername());
		if (vivienda.getPropietario() == propietario || admin != null) {
			Collection<Vivienda> compradas = viviendaService.getCompradas();
			if (!compradas.contains(vivienda)) {
				viviendaService.delete(vivienda);
			} else {
				model.addAttribute("error", "No se puede borrar el anuncio porque la vivienda ha sido comprada");
			}
		}

		return showListViviendas(model, null, null);

	}

	@GetMapping(value = "/publicitar/{viviendaId}")
	public String publicitar(@PathVariable("viviendaId") int viviendaId, ModelMap model) {
		Vivienda vivienda = viviendaService.findViviendaId(viviendaId);
		viviendaService.publicitar(vivienda);
		model.addAttribute("vivienda", vivienda);
		model.addAttribute("success", "La vivienda ha sido publicitada");

		return "redirect:/viviendas/allNew";
	}
}