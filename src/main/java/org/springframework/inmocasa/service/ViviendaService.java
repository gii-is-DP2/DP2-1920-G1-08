package org.springframework.inmocasa.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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


	//Santi-Alvaro
	
	
	//Alvaro-MiguelEmmanuel
	public Collection<Vivienda> findAllNewest(){
		return vr.findAllNewest();
	}
	
	//Alba-Alejandro
	public Vivienda findViviendaById(Integer viviendaId) {
		return vr.findViviendaById(viviendaId);
	}
	
	public Collection<Vivienda> findViviendasALaVenta() {
		Collection<Vivienda> res = vr.findAllNewest();
		res.removeAll(vr.getViviendasCompradas());
		return res;
	}

}
