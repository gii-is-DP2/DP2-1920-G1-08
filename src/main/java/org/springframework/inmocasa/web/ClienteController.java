package org.springframework.inmocasa.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.repository.ClienteRepository;
import org.springframework.inmocasa.service.ClienteService;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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
		return "welcome";
	}

	@GetMapping(value = { "clientes/miPerfil" })
	public ModelAndView showMyProfile() {
		ModelAndView res = new ModelAndView("clientes/profile");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
		String username = userPrincipal.getUsername();
		Cliente cliente = clienteService.findClienteByClienteUsername(username);
		if (cliente != null) {
			res.addObject("cliente", cliente);
		}

		return res;
	}

	@GetMapping("clientes/{clienteId}/edit")
	public ModelAndView editVivienda(@PathVariable("clienteId") int clienteId) {
		ModelAndView mav = new ModelAndView("clientes/registroClientes");
		mav.addObject("cliente", this.clienteService.findClienteById(clienteId));
		return mav;
	}

	@PostMapping(path = "clientes/{clienteId}/save")
	private String processCreationForm(@Valid Cliente cliente, BindingResult res, ModelMap modelMap) {
		if (res.hasErrors()) {
			modelMap.addAttribute("cliente", cliente);
			return "clientes/registroClientes";
		} else {
			clienteService.saveCliente(cliente);
			modelMap.addAttribute("message", "Saved successfully");
		}
		return "clientes/profile";
	}

	// Alvaro-MiguelEmmanuel

	// Alba-Alejandro

}
