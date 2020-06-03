package org.springframework.inmocasa.web;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.enums.Genero;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.inmocasa.web.validator.PropietarioValidator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PropietarioController {

	// Santi-Alvaro

	@Autowired
	PropietarioService propietarioService;

	@InitBinder("propietario")
	public void initCompraBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new PropietarioValidator());
	}
	
//	@Autowired
//	public PropietarioController(PropietarioService propietarioService, UsuarioService userService,
//			AuthoritiesService authoritiesService) {
//		this.propietarioService = propietarioService;
//	}

	@GetMapping(path = "/propietarios/new")
	public String crearPropietario(ModelMap model) {
		Map<String, String> generos = new LinkedHashMap<String, String>();
		for (Genero gen : Genero.values()) {
			generos.put(gen.name(), gen.getDisplayName());
		}
		model.addAttribute("generos", generos);
		
		Propietario propietario = new Propietario();
		model.addAttribute("propietario", propietario);
		return "propietarios/registroPropietarios";
	}

	@PostMapping(path = "/propietarios/save")
	public String guardarPropietario(@Valid Propietario propietario, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("propietario", propietario);
			return "propietarios/registroPropietarios";
		} else {
			if(propietarioService.findByUsername(propietario.getUsername())!= null) {
				model.addAttribute("propietario", propietario);
				model.addAttribute("error", "El usuario ya existe.");
				return "propietarios/registroPropietarios";
			}
			
			propietarioService.savePropietario(propietario);
			model.addAttribute("success", "Propietario registrado correctamente");

		}
		return "welcome";
	}

	@GetMapping(value = { "propietarios/miPerfil" })
	public ModelAndView showMyProfile() {
		ModelAndView res = new ModelAndView("propietarios/profile");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
		String username = userPrincipal.getUsername();
		Propietario propietario = propietarioService.findByUsername(username);
		if (propietario != null) {
			res.addObject("propietario", propietario);
		}

		return res;
	}

	@GetMapping("propietarios/{propietarioId}/edit")
	public String editProfile(@PathVariable("propietarioId") int propietarioId,ModelMap model) {
		String view = "propietarios/registroPropietarios";
		Map<String, String> generos = new LinkedHashMap<String, String>();
		for (Genero gen : Genero.values()) {
			generos.put(gen.name(), gen.getDisplayName());
		}
		model.addAttribute("generos", generos);
		model.addAttribute("propietario", this.propietarioService.findPropietarioById(propietarioId));
		return view;
	}

	@PostMapping(path = "propietarios/{propietarioId}/save")
	public String processUpdateForm(@Valid Propietario propietario, BindingResult res, ModelMap modelMap) {
		if (res.hasErrors()) {
			modelMap.addAttribute("propietario", propietario);
			return "propietarios/registroPropietarios";
		} else {
			propietarioService.savePropietario(propietario);
			modelMap.addAttribute("message", "Saved successfully");
		}
		return "propietarios/profile";
	}

	// Alvaro-MiguelEmmanuel

	// Alba-Alejandro

}
