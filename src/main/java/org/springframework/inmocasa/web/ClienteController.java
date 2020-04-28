package org.springframework.inmocasa.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.service.ClienteService;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClienteController {

	// Santi-Alvaro

	@Autowired
	ClienteService clienteService;
	@Autowired
	PropietarioService propietarioService;

	@GetMapping(path = "/clientes/new")
	public String crearCliente(ModelMap model) {
		Cliente cliente = new Cliente();
		model.addAttribute("cliente", cliente);
		return "clientes/registroClientes";
	}

	@PostMapping(path = "/clientes/save")
	public String guardarCliente(@Valid Cliente cliente, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("cliente", cliente);
			return "clientes/registroClientes";
		} else {
			clienteService.saveCliente(cliente);
			model.addAttribute("message", "Cliente creado");

		}
		return "viviendas/listNewViviendas";
	}

	

	// Alvaro-MiguelEmmanuel

	// Alba-Alejandro

}
