package org.springframework.inmocasa.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Visita;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.service.ClienteService;
import org.springframework.inmocasa.service.VisitaService;
import org.springframework.inmocasa.service.ViviendaService;
import org.springframework.inmocasa.web.validator.VisitaValidator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value="visita")
@Controller
public class VisitaController {

	private VisitaService visitaService;
	private ViviendaService viviendaService;
	private ClienteService clienteService;
	private UsuarioController usuarioController;
	
	
	@InitBinder("visita")
	public void initCompraBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new VisitaValidator());
	}
	
	//Vistas
	private final String VIEW_EDIT_VISITA = "viviendas/visitas/createOrUpdateVisita";
	
	@Autowired
	public VisitaController(VisitaService visitaService, ViviendaService viviendaService, ClienteService clienteService,
			UsuarioController usuarioController) {
		super();
		this.visitaService = visitaService;
		this.viviendaService = viviendaService;
		this.clienteService = clienteService;
		this.usuarioController = usuarioController;
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
			model.addAttribute("error", "No se ha encontrado la vivienda");
			view = null;
		}
		
		return view;
	}

	@PostMapping("/save")
	public String saveVisita(@Valid Visita visita, BindingResult result,ModelMap model) {
		
		if(result.hasErrors()) {
    		return VIEW_EDIT_VISITA;
    	} else {
    		visitaService.save(visita);
    		model.addAttribute("success", "Visita generada correctamente");
    	}
		return usuarioController.showListViviendas(model);
	}

	
	//Alba-Alejandro
	
	
}
