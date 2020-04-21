package org.springframework.inmocasa.service;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

	private ClienteRepository crep;

	@Autowired
	public ClienteService(ClienteRepository clienteR) {
		this.crep = clienteR;
	}

	// Santi-Alvaro
	@Transactional
	public Collection<Cliente> findAll() {
		return crep.findAll();
	}

	public Cliente findClienteByClienteUsername(String username) {
		return crep.findByClienteUsername(username);
	}

	// Alvaro-MiguelEmmanuel
	public List<Cliente> findClienteByUsername(String username) {
		return crep.findByUsername(username);
	}

	// Alba-Alejandro

}
