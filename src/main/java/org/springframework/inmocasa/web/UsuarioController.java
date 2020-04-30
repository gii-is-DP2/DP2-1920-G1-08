package org.springframework.inmocasa.web;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Cliente;

import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Visita;
import org.springframework.inmocasa.service.ClienteService;
import org.springframework.inmocasa.service.VisitaService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;

import javax.validation.Valid;


import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;


@RequestMapping(value="usuario")
@Controller
public class UsuarioController {
	
	VisitaService visitaService;
	ClienteService clienteService;
    PropietarioService propietarioService;

	
	@Autowired
	public UsuarioController(VisitaService visitaService,ClienteService clienteService,PropietarioService propietService) {
		super();
		this.visitaService = visitaService;
		this.clienteService = clienteService;
		this.propietarioService=propietService;
	}

//	@Autowired
//	public UsuarioController(PropietarioService propietService) {
//		this.propietarioService = propietService;
//	}
	// Santi-Alvaro


	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/usuarios/new")
	public String initCreationForm(Map<String, Object> model) {
		Propietario propietario = new Propietario();
		model.put("propietario", propietario);
		return "usuarios/createPropietarioForm";
	}

	@PostMapping(value = "/usuarios/new")
	public String processCreationForm(@Valid Propietario propietario, BindingResult result) {
		if (result.hasErrors()) {
			return "usuarios/createPropietarioForm";
		} else {
			// creating propietario, usuario, and authority
			this.propietarioService.savePropietario(propietario);
			return "redirect:/";
		}
	}

	// Alvaro-MiguelEmmanuel
  
	public String showListViviendas(ModelMap modelMap) {

		User usuario = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Cliente> clientes = clienteService.findClienteByUsername(usuario.getUsername());
		
		Collection<Visita> pVisitas = visitaService.findProximasVisitas(clientes.get(0), LocalDateTime.now());
		modelMap.put("proximasVisitas", pVisitas);
		
		Collection<Visita> vivs = visitaService.findOldVisitas(clientes.get(0), LocalDateTime.now());
		modelMap.put("visitas", vivs);

		return "users/visitas";
	}

	// Alba-Alejandro


}
