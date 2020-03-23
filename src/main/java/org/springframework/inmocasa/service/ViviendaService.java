package org.springframework.inmocasa.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.repository.ViviendaRepository;
import org.springframework.stereotype.Service;

@Service
public class ViviendaService {

	ViviendaRepository vr;

	@Autowired
	public ViviendaService(ViviendaRepository vr) {
		super();
		this.vr = vr;
	}

	// Santi-Alvaro

	public Iterable<Vivienda> findAll() {
		return vr.findAll();
	}


	public Vivienda findViviendaById(int viviendaId) {
		// TODO Auto-generated method stub
		return vr.findById(viviendaId);
	}

	// Alvaro-MiguelEmmanuel
	public Collection<Vivienda> findAllNewest() {
		return vr.findAllNewest();
	}
	
	//Alba-Alejandro
	
	
}
