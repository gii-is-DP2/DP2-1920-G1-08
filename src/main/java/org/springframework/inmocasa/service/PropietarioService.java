package org.springframework.inmocasa.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Cliente;

import javax.transaction.Transactional;

import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.enums.Genero;
import org.springframework.inmocasa.repository.ClienteRepository;
import org.springframework.inmocasa.repository.PropietarioRepository;
import org.springframework.stereotype.Service;

@Service
public class PropietarioService {

	PropietarioRepository propietarioRepository;
	AuthoritiesService authoritiesService;
	ClienteService clienteService;
	ClienteRepository clienterRepository;

	@Autowired
	public PropietarioService(PropietarioRepository propRepo, AuthoritiesService authoritiesService,
			ClienteService clienteService, ClienteRepository clienteRepository) {
		this.propietarioRepository = propRepo;
		this.authoritiesService = authoritiesService;
		this.clienteService = clienteService;
		this.clienterRepository = clienteRepository;
	}

	// Santiago-Alvaro
	public List<Propietario> findPropietarioByUsername(String username) {
		return propietarioRepository.findPropietarioByUsername(username);
	}
	// Alvaro-MiguelEmmanuel

	public Propietario findByUsername(String username) {
		return propietarioRepository.findByUsername(username);
	}
	
	public Propietario findPropietarioById(Integer id) {
		return propietarioRepository.findPropietarioById(id);
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
