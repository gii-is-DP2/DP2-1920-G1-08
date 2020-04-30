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

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/compras")
public class CompraController {

	private static final String VIEWS_COMPRA_CREATE_UPDATE_FORM = "compras/form";

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
	// Alvaro-MiguelEmmanuel

	// Alba-Alejandro
	@InitBinder("compra")
	public void initCompraBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new CompraValidator());
	}

	@GetMapping(value = "/create/{viviendaId}")
	public String create(@PathVariable("viviendaId") Integer viviendaId, ModelMap model) {
		Compra compra = new Compra();
		Vivienda vivienda = viviendaService.findViviendaById(viviendaId);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
		Cliente cliente = clienteService.findClienteByUsername(userPrincipal.getUsername()).get(0);

		compra.setCliente(cliente);
		compra.setVivienda(vivienda);
		compra.setEstado(Estado.PENDIENTE);
		model.put("compra", compra);
		return VIEWS_COMPRA_CREATE_UPDATE_FORM;
	}

	@PostMapping(value = "/create/{viviendaId}")
	public String save(@PathVariable("viviendaId") Integer viviendaId, @Valid Compra compra, BindingResult binding,
			ModelMap model) {
		if (binding.hasErrors()) {
			model.put("compra", compra);
			return VIEWS_COMPRA_CREATE_UPDATE_FORM;
		} else {
			Vivienda vivienda = viviendaService.findViviendaById(viviendaId);

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
			Cliente cliente = clienteService.findClienteByUsername(userPrincipal.getUsername()).get(0);

			compra.setCliente(cliente);
			compra.setVivienda(vivienda);
			compra.setEstado(Estado.PENDIENTE);
			compraService.saveCompra(compra);
			return "redirect:/viviendas/allNew";
		}
	}

	// Santi-Alvaro
	@GetMapping(value = "/{viviendaId}")
	public String showCompra(@PathVariable("viviendaId") int viviendaId, ModelMap model) {
		String view = "compras/showCompraDetails";
		Compra compras = this.compraService.findCompraByViviendaId(viviendaId);
		model.put("compras", compras);
		return view;

	}

	@GetMapping(value = "/{viviendaId}/aceptar")
	public String aceptarCompra(@PathVariable("viviendaId") int viviendaId, ModelMap model) {
		String view = "/viviendas/ofertadas";
		Compra compras = this.compraService.findCompraByViviendaId(viviendaId);
		compras.setEstado(Estado.ACEPTADO);
		compraService.save(compras);
		model.addAttribute("compras", compras);
		model.addAttribute("message", "La oferta ha sido aceptada correctamente");

		return "redirect:" + view;
	}

	@GetMapping(value = "/{viviendaId}/rechazar")
	public String rechazarCompra(@PathVariable("viviendaId") int viviendaId, ModelMap model) {
		String view = "/viviendas/ofertadas";
		Compra compras = this.compraService.findCompraByViviendaId(viviendaId);
		compras.setEstado(Estado.RECHAZADO);
		compraService.deleteById(compras.getId());
		compraService.save(compras);
		model.addAttribute("compras", compras);
		model.addAttribute("message", "La oferta ha sido rechazada correctamente");
		return "redirect:" + view;
	}

}
