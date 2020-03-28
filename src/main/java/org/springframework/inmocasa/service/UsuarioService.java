package org.springframework.inmocasa.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.inmocasa.model.Usuario;
import org.springframework.inmocasa.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	// Santi-Alvaro

	private UsuarioRepository userRepository;

	@Autowired
	public UsuarioService(UsuarioRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional
	public void saveUsuario(Usuario usuario) throws DataAccessException {

		userRepository.save(usuario);
	}

	// Alvaro-MiguelEmmanuel

	// Alba-Alejandro

}
