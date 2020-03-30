package org.springframework.inmocasa.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
		String view = "/propietarios/" + propietario.getId();
		if (result.hasErrors()) {
			model.addAttribute("propietario", propietario);
			return "propietarios/registroPropietarios";
		} else {
			propietarioService.savePropietario(propietario);
			model.addAttribute("message", "Propietario creado");

		}
		return  view;
	}

	// Alvaro-MiguelEmmanuel

	// Alba-Alejandro

}
