package org.springframework.inmocasa.service;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

	ClienteRepository crep;
	AuthoritiesService authoritiesService;

	@Autowired
	public ClienteService(ClienteRepository crep, AuthoritiesService authoritiesService) {
		this.crep = crep;
		this.authoritiesService = authoritiesService;
	}

	// Santi-Alvaro
	@Transactional
	public Collection<Cliente> findAll() {
		return crep.findAll();
	}

	public Cliente findClienteByClienteUsername(String username) {
		return crep.findByClienteUsername(username);
	}

	public void saveCliente(@Valid Cliente cliente) {

		crep.save(cliente);

		authoritiesService.saveAuthorities(cliente.getUsername(), "cliente");

	}

	// Alvaro-MiguelEmmanuel
	public List<Cliente> findClienteByUsername(String username) {
		return crep.findByUsername(username);
	}

	// Alba-Alejandro

}
