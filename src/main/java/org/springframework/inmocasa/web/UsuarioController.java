package org.springframework.inmocasa.web;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Usuario;
import org.springframework.inmocasa.model.Visita;
import org.springframework.inmocasa.service.ClienteService;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.inmocasa.service.UsuarioService;
import org.springframework.inmocasa.service.VisitaService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@RequestMapping(value="usuario")
@Controller
public class UsuarioController {
	
	VisitaService visitaService;
	ClienteService clienteService;
    PropietarioService propietarioService;
    UsuarioService usuarioService;
	

	@Autowired
	public UsuarioController(VisitaService visitaService,ClienteService clienteService, PropietarioService clinicService,
								UsuarioService usuarioService) {
		super();
		this.visitaService = visitaService;
		this.clienteService = clienteService;
		this.propietarioService = clinicService;
		this.usuarioService = usuarioService;

	}

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
	
//  	@GetMapping(value = {"/miPerfil"})
//  	public String showMyProfile(ModelMap model) {
//  		
//  		String view = "users/profile";
//  		User usuario = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//  		Propietario prop = propietarioService.findByUsername(usuario.getUsername());
//  		if(prop != null) {
//  			model.addAttribute("propietario", prop);
//  		}
//  		
//  		model.put("user", usuario);
//  		return view;
//  		
//  	}
  	@GetMapping(value= {"/miPerfil"})
  	public ModelAndView showMyProfile() {
  		ModelAndView res = new ModelAndView("users/profile");
  		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  		Usuario usuario = usuarioService.findUsuarioByUsername(user.getUsername());
		Propietario prop = propietarioService.findByUsername(user.getUsername());
		if(prop != null) {
			res.addObject("propietario", prop);
		}
		
		res.addObject("user", usuario);
		return res;
  	}
  	

}
