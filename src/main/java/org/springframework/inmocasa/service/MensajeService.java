package org.springframework.inmocasa.service;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Mensaje;
import org.springframework.inmocasa.repository.MensajeRepository;
import org.springframework.stereotype.Service;

@Service
public class MensajeService {


	MensajeRepository mr;

	@Autowired
	public MensajeService(MensajeRepository mr) {
		super();
		this.mr = mr;
	}

	// Santi-Alvaro

	public Collection<Mensaje> findAll() {
		return mr.findAll();
	}

	public Collection<Mensaje> findMensajeByEmisorId(Integer emisor) {
		return mr.findByEmisorId(emisor);
	}

	public Collection<Mensaje> findMensajeByReceptor(Integer receptor) {
		return mr.findByReceptorId(receptor);
	}

	public void save( Mensaje mensaje) {
		// TODO Auto-generated method stub
		mr.save(mensaje);
		
	}

	// Alvaro-MiguelEmmanuel

	// Alba-Alejandro

}
