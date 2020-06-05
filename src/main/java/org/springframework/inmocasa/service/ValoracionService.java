package org.springframework.inmocasa.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Valoracion;
import org.springframework.inmocasa.model.Visita;
import org.springframework.inmocasa.repository.ValoracionRepository;
import org.springframework.stereotype.Service;

@Service
public class ValoracionService {

	
	ValoracionRepository valoracionRepository;

	@Autowired
	public ValoracionService(ValoracionRepository valoracionRepository) {
		super();
		this.valoracionRepository = valoracionRepository;
	}

	//Santi-Alvaro
	
	
	//Alvaro-MiguelEmmanuel
	public List<Valoracion> findByVisita(Visita visita) {
		return valoracionRepository.findByVisita(visita);
	}

	public Valoracion save(@Valid Valoracion valoracion) {
		return valoracionRepository.save(valoracion);
	}
	
	public List<Valoracion> findAllByPropietario(Propietario p){
		return valoracionRepository.findAllByPropietario(p);
	}
	
	//Alba-Alejandro
	
	
}
