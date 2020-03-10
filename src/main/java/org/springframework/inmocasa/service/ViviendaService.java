package org.springframework.inmocasa.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.repository.ViviendaRepository;
import org.springframework.stereotype.Service;

@Service
public class ViviendaService {
	
	@Autowired
	ViviendaRepository vr;

	//Santi-Alvaro
	
	
	//Alvaro-MiguelEmmanuel
	public Collection<Vivienda> findAllNewest(){
		return vr.findAllNewest();
	}
	
	//Alba-Alejandro
	
	

}
