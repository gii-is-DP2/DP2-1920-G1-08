package org.springframework.inmocasa.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Habitacion;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.repository.HabitacionRepository;
import org.springframework.inmocasa.repository.ViviendaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class ViviendaService {

	ViviendaRepository vr;
	HabitacionRepository hr;
	PropietarioService pr;

	@Autowired
	public ViviendaService(ViviendaRepository vr, HabitacionRepository hr, PropietarioService pr) {
		super();
		this.vr = vr;
		this.hr = hr;
		this.pr = pr;
	}

	// Santi-Alvaro

	public Iterable<Vivienda> findAll() {
		return vr.findAll();
	}

	public Optional<Vivienda> findViviendaById(int viviendaId) {
		return vr.findById(viviendaId);
	}

	// Alvaro-MiguelEmmanuel
	public Collection<Vivienda> findAllNewest() {
		Collection<Vivienda> publicitadas = vr.getPublicitadas();
		Collection<Vivienda> noPublicitadas = vr.getNOPublicitadas();
		Collection<Vivienda> viviendasCompradas = vr.getViviendasCompradas();
		Collection<Vivienda> viviendas = new ArrayList<Vivienda>();
		viviendas.addAll(publicitadas);
		viviendas.addAll(noPublicitadas);
		viviendas.removeAll(viviendasCompradas);
		return viviendas;
	}

	public void save(Vivienda vivienda) {
		vr.save(vivienda);
	}

	// Alba-Alejandro

	public Vivienda findViviendaId(Integer viviendaId) {
		return vr.findViviendaById(viviendaId);
	}

	public Collection<Vivienda> findViviendasALaVenta() {
		Collection<Vivienda> res = vr.findAllNewest();
		res.removeAll(vr.getViviendasCompradas());
		return res;
	}

	public Collection<Vivienda> findViviendaByPrecio(Integer precioMin, Integer precioMax) {
		Collection<Vivienda> res = vr.findViviendaByPrecio(precioMin, precioMax);
		res.removeAll(vr.getViviendasCompradas());
		return res;
	}

	public Collection<Vivienda> getCompradas() {
		return vr.getViviendasCompradas();
	}

	public void delete(Vivienda vivienda) {
		Collection<Vivienda> compradas = vr.getViviendasCompradas();
		if (!compradas.contains(vivienda)) {
			Collection<Habitacion> habitaciones;
			habitaciones = vr.getHabitacionesVivienda(vivienda.getId());
			if (habitaciones != null) {
				hr.deleteAll(habitaciones);
			}
			vr.delete(vivienda);
		}
	}

	public void publicitar(Vivienda vivienda) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
		Propietario propietario = pr.findByUsername(userPrincipal.getUsername());
		if (vivienda.getPropietario().equals(propietario)) {
			vivienda.setPublicitado(true);
			vivienda.setFechaPublicacion(LocalDate.now());
			this.save(vivienda);
		}
	}

}
