package org.springframework.inmocasa.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Visita;
import org.springframework.inmocasa.repository.VisitaRepository;
import org.springframework.stereotype.Service;

@Service
public class VisitaService {

	VisitaRepository vr;
	
	@Autowired
	public VisitaService(VisitaRepository vr) {
		super();
		this.vr = vr;
	}
	
	//Santi-Alvaro
	
	
	//Alvaro-MiguelEmmanuel
	public Optional<Visita> findById(int id) {
		return vr.findById(id);
	}
	
	public Visita save(Visita v) {
		return vr.save(v);
	}

	public Collection<Visita> findAllByCliente(Cliente cliente) {
		return vr.findAllByCliente(cliente);
	}

	public Collection<Visita> findProximasVisitas(Cliente cliente, LocalDateTime localDateTime) {
		return vr.findProximasVisitas(cliente, localDateTime);
	}
	
	public Collection<Visita> findOldVisitas(Cliente cliente, LocalDateTime localDateTime) {
		return vr.findOldVisitas(cliente, localDateTime);
	}
	
	//Alba-Alejandro
	
	
}
