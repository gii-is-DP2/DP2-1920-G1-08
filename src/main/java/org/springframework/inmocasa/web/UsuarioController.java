package org.springframework.inmocasa.web;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Visita;
import org.springframework.inmocasa.service.ClienteService;
import org.springframework.inmocasa.service.VisitaService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value="usuario")
@Controller
public class UsuarioController {
	
	VisitaService visitaService;
	ClienteService clienteService;
	
	@Autowired
	public UsuarioController(VisitaService visitaService,ClienteService clienteService) {
		super();
		this.visitaService = visitaService;
		this.clienteService = clienteService;
	}



	//Santi-Alvaro
	
	
	//Alvaro-MiguelEmmanuel
	@GetMapping(value = { "/misVisitas" })
	public String showListViviendas(ModelMap modelMap) {

		User usuario = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Cliente> clientes = clienteService.findClienteByUsername(usuario.getUsername());
		
		Collection<Visita> pVisitas = visitaService.findProximasVisitas(clientes.get(0), LocalDateTime.now());
		modelMap.put("proximasVisitas", pVisitas);
		
		Collection<Visita> vivs = visitaService.findOldVisitas(clientes.get(0), LocalDateTime.now());
		modelMap.put("visitas", vivs);

		return "users/visitas";
	}
	
	//Alba-Alejandro
	
	
}
