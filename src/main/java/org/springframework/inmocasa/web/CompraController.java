package org.springframework.inmocasa.web;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Compra;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.model.enums.Estado;
import org.springframework.inmocasa.service.ClienteService;
import org.springframework.inmocasa.service.CompraService;
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
@RequestMapping("/compra")
public class CompraController {

	private static final String VIEWS_COMPRA_CREATE_UPDATE_FORM = "compra/form";

	CompraService compraService;

	ViviendaService viviendaService;

	ClienteService clienteService;

	@Autowired
	public CompraController(CompraService compraService, ViviendaService viviendaService,
			ClienteService clienteService) {
		this.compraService = compraService;
		this.viviendaService = viviendaService;
		this.clienteService = clienteService;
	}

	// Santi-Alvaro

	// Alvaro-MiguelEmmanuel

	// Alba-Alejandro
	@GetMapping(value = "/create/{viviendaId}")
	public String create(@PathVariable("viviendaId") Integer viviendaId, ModelMap model) {
		Compra compra = new Compra();
		Vivienda vivienda = viviendaService.findViviendaById(viviendaId);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
		Cliente cliente = clienteService.findByUsername(userPrincipal.getUsername());

		compra.setCliente(cliente);
		compra.setVivienda(vivienda);
		compra.setEstado(Estado.PENDIENTE);
		model.put("compra", compra);
		return VIEWS_COMPRA_CREATE_UPDATE_FORM;
	}

	@PostMapping(value = "/create/{viviendaId}")
	public String save(@Valid Compra compra, @PathVariable("viviendaId") Integer viviendaId, BindingResult binding,
			ModelMap model) {
		if (binding.hasErrors()) {
			model.put("compra", compra);
			return VIEWS_COMPRA_CREATE_UPDATE_FORM;
		} else {
			Vivienda vivienda = viviendaService.findViviendaById(viviendaId);

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
			Cliente cliente = clienteService.findByUsername(userPrincipal.getUsername());

			compra.setCliente(cliente);
			compra.setVivienda(vivienda);
			compra.setEstado(Estado.PENDIENTE);
			compraService.saveCompra(compra); // error porq id compra se pone null
			return "redirect:/vivienda/" + vivienda.getId();
		}
	}

}
