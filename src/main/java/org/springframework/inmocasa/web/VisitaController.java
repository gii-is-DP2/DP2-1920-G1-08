package org.springframework.inmocasa.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Visita;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.service.ClienteService;
import org.springframework.inmocasa.service.VisitaService;
import org.springframework.inmocasa.service.ViviendaService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value="visita")
@Controller
public class VisitaController {

	private VisitaService visitaService;
	private ViviendaService viviendaService;
	private ClienteService clienteService;
	
	//Vistas
	private final String VIEW_EDIT_VISITA = "viviendas/visitas/createOrUpdateVisita";
	
	@Autowired
	public VisitaController(VisitaService visitaService, ViviendaService viviendaService, ClienteService clienteService) {
		super();
		this.visitaService = visitaService;
		this.viviendaService = viviendaService;
		this.clienteService = clienteService;
	}
	
	//Santi-Alvaro
	
	
	//Alvaro-MiguelEmmanuel
	@GetMapping(value="/vivienda/{viviendaId}/new")
	public String newVisita(@PathVariable("viviendaId") int vivId, ModelMap model) {
		Visita visita = new Visita();
		String view = VIEW_EDIT_VISITA;
		
		Authentication x = SecurityContextHolder.getContext().getAuthentication();
		List<Cliente> clientes = clienteService.findClienteByUsername(((User)x.getPrincipal()).getUsername());		

		Vivienda v = viviendaService.findViviendaById(vivId).orElse(null);
		
		if( clientes.size() == 1) {
			visita.setVivienda(v);
			visita.setCliente(clientes.get(0));
			model.addAttribute("visita", visita);
		}else {
			model.addAttribute("msgError", "No se ha encontrado la vivienda");
			view = null;
		}
		
		return view;
	}

	@PostMapping("/save")
	public String saveVisita(@Valid Visita visita, ModelMap model) {
		
		Visita v = visitaService.save(visita);
		
		return VIEW_EDIT_VISITA;
	}

	
	//Alba-Alejandro
	
	
}
