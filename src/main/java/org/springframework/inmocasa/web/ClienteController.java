package org.springframework.inmocasa.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.service.ClienteService;
import javax.validation.Valid;

import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.inmocasa.service.ViviendaService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	ClienteService clienteService;
	
	ViviendaService viviendaService;
	PropietarioService propietarioService;
	
	@Autowired
	public ClienteController(ClienteService clienteService, ViviendaService viviendaService,PropietarioService propietarioService) {
		this.clienteService = clienteService;
		this.viviendaService = viviendaService;
		this.propietarioService = propietarioService;
	}


	// Santi-Alvaro


	@GetMapping(path = "/new")
	public String crearCliente(ModelMap model) {
		Cliente cliente = new Cliente();
		model.addAttribute("cliente", cliente);
		return "clientes/registroClientes";
	}

	@PostMapping(path = "/save")
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

	@GetMapping(value = { "/miPerfil" })
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

	@GetMapping("/{clienteId}/edit")
	public String editProfile(@PathVariable("clienteId") int clienteId,ModelMap model) {
		String view = "clientes/registroClientes";
		model.addAttribute("cliente",this.clienteService.findClienteById(clienteId));
		return view;
	}

	@PostMapping(path = "/{clienteId}/save")
	public String processUpdateForm(@Valid Cliente cliente, BindingResult res, ModelMap modelMap) {
		if (res.hasErrors()) {
			modelMap.addAttribute("cliente", cliente);
			return "clientes/registroClientes";
		} else {
			clienteService.saveCliente(cliente);
			modelMap.addAttribute("message", "Saved successfully");
		}
		return "clientes/profile";
	}

	
	
	//Alvaro-MiguelEmmanuel
	
	@GetMapping(value = "/{viviendaId}/favoritos")
	public String añadirFavorito(@PathVariable("viviendaId") int viviendaId, ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
		Cliente cliente = clienteService.findByUsername(userPrincipal.getUsername());
		Vivienda vivienda = clienteService.findViviendaById(viviendaId);
		List<Vivienda> favoritas = new ArrayList<>();
		favoritas.addAll(cliente.getFavoritas());
		favoritas.add(vivienda);
		cliente.setFavoritas(favoritas);
		vivienda.setFav(true);
		clienteService.save(cliente);
		viviendaService.save(vivienda);
		model.addAttribute("clientes", cliente);
		model.addAttribute("message", "La vivienda ha sido añadida a favoritos correctamente");
		return favoritos(model);
	}
	
	
	@GetMapping(value = "/lista/favoritas")
	public String favoritos(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
		Cliente cliente = clienteService.findByUsername(userPrincipal.getUsername());
		model.addAttribute("viviendas", cliente.getFavoritas());
		return "viviendas/listNewViviendas";
		
	}
	//Alba-Alejandro
	
	
}
