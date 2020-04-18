package org.springframework.inmocasa.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Mensaje;
import org.springframework.inmocasa.repository.PropietarioRepository;
import org.springframework.inmocasa.repository.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class PropietarioService {

PropietarioRepository propietarioRepository;
UsuarioService usuarioService;
AuthoritiesService authoritiesService;

	
	//Alvaro-MiguelEmmanuel
	
	public Propietario findByUsername(String username) {
		return propietarioRepository.findByUsername(username);
	}


	public void savePropietario(@Valid Propietario propietario) {

		propietarioRepository.save(propietario);

		usuarioService.saveUsuario(propietario);

		authoritiesService.saveAuthorities(propietario.getUsername(), "propietario");

	}

	public List<Propietario> findAll() {
		return 	propietarioRepository.findAll();

	}

	//Alba-Alejandro
	
	
	
}

