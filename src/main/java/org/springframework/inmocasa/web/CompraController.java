package org.springframework.inmocasa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Compra;
import org.springframework.inmocasa.service.CompraService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

@Controller
public class CompraController {

	@Autowired
	private CompraService compraService;

	// Santi-Alvaro
	public String listadoCompras(ModelMap model) {
		String vista = "/listaOferta";
		Iterable<Compra> compra = compraService.findAll();
		model.addAttribute("ofertas", compra);
		return vista;
	}

	// Alvaro-MiguelEmmanuel

	// Alba-Alejandro

}
