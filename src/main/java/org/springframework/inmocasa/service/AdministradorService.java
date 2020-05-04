package org.springframework.inmocasa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Administrador;
import org.springframework.inmocasa.repository.AdministradorRepository;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService {
	
	private AdministradorRepository adminRep;
	

	@Autowired
	public AdministradorService(AdministradorRepository administradorR) {
		this.adminRep = administradorR;
	}
	
	//Alba-Alejandro
	public Administrador findAdministradorByUsername(String username) {
		return adminRep.findByUsername(username);
	}

}
