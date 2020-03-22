package org.springframework.inmocasa.service;

import javax.transaction.Transactional;

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
	@Transactional
	public Iterable<Compra> findAll() {
		return cr.findOfertas();
	}

	// Alvaro-MiguelEmmanuel

	// Alba-Alejandro

}
