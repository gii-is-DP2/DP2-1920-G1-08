package org.springframework.inmocasa.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Denuncia;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.repository.DenunciaRepository;
import org.springframework.inmocasa.repository.ViviendaRepository;
import org.springframework.stereotype.Service;


@Service
public class DenunciaService {
	
	DenunciaRepository dr;
	ViviendaRepository vr;

	@Autowired
	public DenunciaService(DenunciaRepository dr) {
		super();
		this.dr = dr;
	}

	public Optional<Vivienda> findViviendaById(int viviendaId) {
		return vr.findById(viviendaId);
	}
	
	public void save(Denuncia denuncia) {
		   dr.save(denuncia);
		}
	
}
