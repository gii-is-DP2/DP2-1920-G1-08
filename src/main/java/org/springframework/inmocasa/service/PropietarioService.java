package org.springframework.inmocasa.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.dao.DataAccessException;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.repository.PropietarioRepository;
import org.springframework.stereotype.Service;

@Service
public class PropietarioService {

	private PropietarioRepository propietarioRepository;

	private UsuarioService usuarioService;

	private AuthoritiesService authoritiesService;

	// Santi-Alvaro
	@Transactional
	public void savePropietario(Propietario propietario)  {

		propietarioRepository.save(propietario);

		usuarioService.saveUsuario(propietario);

		authoritiesService.saveAuthorities(propietario.getUsername(), "propietario");

	}


	// Alvaro-MiguelEmmanuel

	// Alba-Alejandro

}
