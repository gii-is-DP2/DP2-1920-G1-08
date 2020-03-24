package org.springframework.inmocasa.service;

import java.util.Collection;

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
	
//	public Collection<Vivienda> findViviendaByPrecio(Integer preciomin, Integer preciomax){
//		Collection<Vivienda> res = vr.findViviendaByPrecio(preciomin, preciomax);
//		return res;
//	}


	public void delete(Vivienda vivienda) {
		Collection<Habitacion> habitaciones ;
		habitaciones = vr.getHabitacionesVivienda(vivienda.getId());
		hr.deleteAll(habitaciones);
		vr.delete(vivienda);
	}
	
	

}
