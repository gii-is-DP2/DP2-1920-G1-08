package org.springframework.inmocasa.service;

import org.springframework.beans.factory.annotation.Autowired;


import javax.transaction.Transactional;

import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.repository.PropietarioRepository;
import org.springframework.stereotype.Service;

@Service
public class PropietarioService {


		propietarioRepository.save(propietario);

		usuarioService.saveUsuario(propietario);

		authoritiesService.saveAuthorities(propietario.getUsername(), "propietario");

	}

	
	//Alvaro-MiguelEmmanuel
	
	public Propietario findByUsername(String username) {
		return propietarioRepository.findByUsername(username);
	}
	
	//Alba-Alejandro
	
	

}
