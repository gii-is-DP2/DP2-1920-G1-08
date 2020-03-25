package org.springframework.inmocasa.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
	
	ClienteRepository clienteRepository;
	
	@Autowired
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	private ClienteRepository crep;
	
	@Autowired
	public ClienteService(ClienteRepository clienteR) {
		this.crep = clienteR;
	}
	
	//Santi-Alvaro
	
	
	//Alvaro-MiguelEmmanuel
	public List<Cliente> findClienteByUsername(String username) {
		return crep.findByUsername(username);
	}
	
	//Alba-Alejandro
	
	public Cliente findByUsername(String username) {
		return clienteRepository.findByUsername(username);
	}
	
}
