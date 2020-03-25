package org.springframework.inmocasa.web;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.service.AuthoritiesService;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.inmocasa.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PropietarioController {

	// Santi-Alvaro

	private final PropietarioService propietarioService;

	@Autowired
	public PropietarioController(PropietarioService propietarioService, UsuarioService userService,
			AuthoritiesService authoritiesService) {
		this.propietarioService = propietarioService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/propietarios/new")
	public String initCreationForm(Map<String, Object> model) {
		Propietario propietario = new Propietario();
		model.put("propietario", propietario);
		return "propietarios/registroPropietarios";
	}

	@PostMapping(value = "propietarios/new")
	public String processCreationForm(@Valid Propietario propietario, BindingResult result) {
		if (result.hasErrors()) {
			return "propietarios/registroPropietarios";
		} else {
			this.propietarioService.savePropietario(propietario);

			return "redirect:/propietarios/" + propietario.getId();
		}
	}

	// Alvaro-MiguelEmmanuel

	// Alba-Alejandro

}
