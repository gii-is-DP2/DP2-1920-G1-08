package org.springframework.inmocasa.service;

import javax.validation.Valid;

import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.repository.PropietarioRepository;
import org.springframework.inmocasa.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

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

	
	//Alba-Alejandro
	
	
	
}

