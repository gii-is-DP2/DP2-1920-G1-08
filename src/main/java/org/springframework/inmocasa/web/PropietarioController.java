package org.springframework.inmocasa.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PropietarioController {

	// Santi-Alvaro

	@Autowired
	PropietarioService propietarioService;

//	@Autowired
//	public PropietarioController(PropietarioService propietarioService, UsuarioService userService,
//			AuthoritiesService authoritiesService) {
//		this.propietarioService = propietarioService;
//	}

	@GetMapping(path = "/propietarios/new")
	public String crearPropietario(ModelMap model) {
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
			propietarioService.savePropietario(propietario);
			model.addAttribute("message", "Propietario creado");

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
	public ModelAndView editVivienda(@PathVariable("propietarioId") int propietarioId) {
		ModelAndView mav = new ModelAndView("propietarios/registroPropietarios");
		mav.addObject("propietario", this.propietarioService.findPropietarioById(propietarioId));
		return mav;
	}

	@PostMapping(path = "propietarios/{propietarioId}/save")
	private String processCreationForm(@Valid Propietario propietario, BindingResult res, ModelMap modelMap) {
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
