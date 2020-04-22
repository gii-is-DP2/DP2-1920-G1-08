package org.springframework.inmocasa.service;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.repository.PropietarioRepository;
import org.springframework.stereotype.Service;

@Service
public class PropietarioService {

	PropietarioRepository propietarioRepository;
	AuthoritiesService authoritiesService;

	@Autowired
	public PropietarioService(PropietarioRepository propRepo, AuthoritiesService authoritiesService) {
		this.propietarioRepository = propRepo;
		this.authoritiesService = authoritiesService;
	}

	// Santiago-Alvaro
	public List<Propietario> findPropietarioByUsername(String username) {
		return propietarioRepository.findPropietarioByUsername(username);
	}
	// Alvaro-MiguelEmmanuel

	public Propietario findByUsername(String username) {
		return propietarioRepository.findByUsername(username);
	}

	public void savePropietario(@Valid Propietario propietario) {

		propietarioRepository.save(propietario);

		authoritiesService.saveAuthorities(propietario.getUsername(), "propietario");

	}

	public Collection<Propietario> findAll() {
		return propietarioRepository.findAll();

	}

	// Alba-Alejandro

}
