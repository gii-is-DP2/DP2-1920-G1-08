package org.springframework.inmocasa.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Compra;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.model.enums.Estado;
import org.springframework.inmocasa.service.CompraService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import sun.util.logging.resources.logging;

@Controller
public class CompraController {

	@Autowired
	private CompraService compraService;

	// Santi-Alvaro
	@GetMapping(value = "/compras/{viviendaId}")
	public String showCompra(@PathVariable("viviendaId") int viviendaId, ModelMap model) {
		String view = "compras/showCompraDetails";
		Compra compras = this.compraService.findCompraByViviendaId(viviendaId);
		model.put("compras", compras);
		return view;

	}

	@GetMapping(value = "/compras/{viviendaId}/aceptar")
	public String aceptarCompra(@PathVariable("viviendaId") int viviendaId, ModelMap model) {
		String view = "/viviendas/ofertadas";
		Compra compras = this.compraService.findCompraByViviendaId(viviendaId);
		compras.setEstado(Estado.ACEPTADO);
		compraService.save(compras);
		model.addAttribute("compras", compras);
		model.addAttribute("message", "La oferta ha sido aceptada correctamente");

		return "redirect:" + view;
	}

	@GetMapping(value = "/compras/{viviendaId}/rechazar")
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
	// Alvaro-MiguelEmmanuel

	// Alba-Alejandro
}
