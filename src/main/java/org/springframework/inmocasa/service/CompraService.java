package org.springframework.inmocasa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Compra;
import org.springframework.inmocasa.repository.CompraRepository;
import org.springframework.stereotype.Service;

@Service
public class CompraService {

	CompraRepository cr;

	@Autowired
	public CompraService(CompraRepository cr) {
		super();
		this.cr = cr;
	}

	// Santi-Alvaro
	public Iterable<Compra> findAll() {
		return cr.findAll();
	}

	// Alvaro-MiguelEmmanuel

	// Alba-Alejandro

}
