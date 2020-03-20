package org.springframework.inmocasa.service;

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

	//Santi-Alvaro
	
	
	//Alvaro-MiguelEmmanuel
	
	
	//Alba-Alejandro
	
	public Cliente findByUsername(String username) {
		return clienteRepository.findByUsername(username);
	}
	
}
