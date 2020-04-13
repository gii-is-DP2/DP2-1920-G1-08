package org.springframework.inmocasa.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Mensaje;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.repository.MensajeRepository;
import org.springframework.stereotype.Service;

@Service
public class MensajeService {

	// Santi-Alvaro

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

	public Collection<Mensaje> findMensajeByEmisor(Cliente emisor) {
		return mr.findByEmisor(emisor);
	}

	public Collection<Mensaje> findMensajeByReceptor(Propietario receptor) {
		return mr.findByReceptor(receptor);
	}

	// Alvaro-MiguelEmmanuel

	// Alba-Alejandro

}
