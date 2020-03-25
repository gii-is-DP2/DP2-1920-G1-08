package org.springframework.inmocasa.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Habitacion;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.repository.HabitacionRepository;
import org.springframework.inmocasa.repository.ViviendaRepository;
import org.springframework.stereotype.Service;

@Service
public class ViviendaService {

	ViviendaRepository vr;
	HabitacionRepository hr;

	@Autowired
	public ViviendaService(ViviendaRepository vr, HabitacionRepository hr) {
		super();
		this.vr = vr;
		this.hr = hr;
	}

	// Santi-Alvaro

	public Iterable<Vivienda> findAll() {
		return vr.findAll();
	}


	public Optional<Vivienda> findViviendaById(int viviendaId) {
		// TODO Auto-generated method stub
		return vr.findById(viviendaId);
	}

	// Alvaro-MiguelEmmanuel
	public Collection<Vivienda> findAllNewest() {
		return vr.findAllNewest();
	}
	
	public void save(Vivienda vivienda) {
		   vr.save(vivienda);
		}
	
	//Alba-Alejandro
	public Vivienda findViviendaId(Integer viviendaId) {
		return vr.findViviendaById(viviendaId);
	}
	
	public Collection<Vivienda> findViviendasALaVenta() {
		Collection<Vivienda> res = vr.findAllNewest();
		res.removeAll(vr.getViviendasCompradas());
		return res;
	}
	
//	public Collection<Vivienda> findViviendaByPrecio(Integer preciomin, Integer preciomax){
//		Collection<Vivienda> res = vr.findViviendaByPrecio(preciomin, preciomax);
//		return res;
//	}


	public void delete(Vivienda vivienda) {
		Collection<Habitacion> habitaciones;
		habitaciones = vr.getHabitacionesVivienda(vivienda.getId());
		hr.deleteAll(habitaciones);
		vr.delete(vivienda);
	}
	
	
}
