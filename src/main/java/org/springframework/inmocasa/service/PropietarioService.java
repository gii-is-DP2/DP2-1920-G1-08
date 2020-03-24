package org.springframework.inmocasa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.repository.PropietarioRepository;
import org.springframework.stereotype.Service;

@Service
public class PropietarioService {
	
	   PropietarioRepository propietarioRepository;
		
		@Autowired
		public PropietarioService(PropietarioRepository propietarioRepository) {
			this.propietarioRepository = propietarioRepository;
		}
	//Santi-Alvaro
	
	
	//Alvaro-MiguelEmmanuel
	
	public Propietario findByUsername(String username) {
		return propietarioRepository.findByUsername(username);
	}
	
	//Alba-Alejandro
	
	
}
