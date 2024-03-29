package org.springframework.inmocasa.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Compra;
import org.springframework.inmocasa.model.Denuncia;
import org.springframework.inmocasa.model.Habitacion;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Visita;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.model.enums.Estado;
import org.springframework.inmocasa.repository.ClienteRepository;
import org.springframework.inmocasa.repository.CompraRepository;
import org.springframework.inmocasa.repository.DenunciaRepository;
import org.springframework.inmocasa.repository.HabitacionRepository;
import org.springframework.inmocasa.repository.VisitaRepository;
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
	ClienteRepository clienteRepository;
	VisitaRepository visitaRepository;
	DenunciaRepository denunciaRepository;
	CompraRepository compraRepository;
	

	@Autowired
	public ViviendaService(ViviendaRepository vr, HabitacionRepository hr, PropietarioService pr,
							VisitaRepository visitaRepository, ClienteRepository clienteRepository,
							DenunciaRepository denunciaRepository, CompraRepository compraRepository) {
		super();
		this.vr = vr;
		this.hr = hr;
		this.pr = pr;
		this.clienteRepository = clienteRepository;
		this.visitaRepository = visitaRepository;
		this.denunciaRepository = denunciaRepository;
		this.compraRepository = compraRepository;
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
	
	public Vivienda ViviendaById(int viviendaId) {
		// TODO Auto-generated method stub
		return vr.ViviendaById(viviendaId);
	}
	
	public List<Vivienda> findAllByPropietario(Propietario p){
		return vr.findAllByPropietario(p);
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
	
	public Integer precioMinViviendas() {
		return vr.precioMinViviendas();
	}
	
	public Integer precioMaxViviendas() {
		return vr.precioMaxViviendas();
	}

	public Collection<Vivienda> findViviendaByPrecio(Integer precioMin, Integer precioMax) {
		Collection<Vivienda> publicitadas = vr.getPublicitadas();
		Collection<Vivienda> viviendas = new ArrayList<Vivienda>();
		Collection<Vivienda> res = vr.findViviendaByPrecio(precioMin, precioMax);
		for(Vivienda v : publicitadas) {
			if(res.contains(v)) {
				viviendas.add(v);
			}
		}
		for(Vivienda v : res) {
			if(!viviendas.contains(v)) {
				viviendas.add(v);
			}
		}
		viviendas.removeAll(vr.getViviendasCompradas());
		return viviendas;
	}
	
	public Collection<Vivienda> findViviendaByZona(String zona) {
		Collection<Vivienda> publicitadas = vr.getPublicitadas();
		Collection<Vivienda> viviendas = new ArrayList<Vivienda>();
		Collection<Vivienda> res = vr.findViviendaByZona(zona);
		for(Vivienda v : publicitadas) {
			if(res.contains(v)) {
				viviendas.add(v);
			}
		}
		for(Vivienda v : res) {
			if(!viviendas.contains(v)) {
				viviendas.add(v);
			}
		}
		viviendas.removeAll(vr.getViviendasCompradas());
		return viviendas;
	}
	
	
	public Boolean esFavorita(List<Vivienda> favoritas) {
		
		return null;
	}
	
	public Collection<String> findZonas() {
		Collection<String> zonas = vr.findAllZonas();
		return zonas;
	}
	
	public Collection<Vivienda> findViviendaByNumHabitacion(Integer num) {
		Collection<Vivienda> publicitadas = vr.getPublicitadas();
		Collection<Vivienda> viviendas = new ArrayList<Vivienda>();
		Collection<Vivienda> vivs = vr.findAllNewest();
		Collection<Vivienda> res = new ArrayList<Vivienda>();
		for(Vivienda v : vivs) {
			Integer vivId = v.getId();
			//Collection<Habitacion> habitaciones = vr.findHabitacionesByVivienda(vivId);
			Integer numHabitaciones = vr.findNumHabitacionesByVivienda(vivId);
			if(numHabitaciones == num) {
				res.add(v);
			}
		}
		for(Vivienda v : publicitadas) {
			if(res.contains(v)) {
				viviendas.add(v);
			}
		}
		for(Vivienda v : res) {
			if(!viviendas.contains(v)) {
				viviendas.add(v);
			}
		}		
		viviendas.removeAll(vr.getViviendasCompradas());
		return viviendas;
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
			Collection<Visita> visitas;
			visitas = visitaRepository.findVisitasByVivienda(vivienda.getId());
			if(visitas != null) {
				visitaRepository.deleteAll(visitas);
			}
			Collection<Cliente> clientes = clienteRepository.findAll();
			for(Cliente c : clientes) {
				if(c.getFavoritas().contains(vivienda)) {
					c.getFavoritas().remove(vivienda);
				}
			}
			Collection<Denuncia> denuncias = denunciaRepository.findDenunciasByViviendaId(vivienda.getId());
			if(denuncias != null) {
				denunciaRepository.deleteAll(denuncias);
			}
			
			Collection<Compra> compras = compraRepository.findAllComprasByVivienda(vivienda);
			for(Compra c : compras) {
				if(c.getEstado() != Estado.ACEPTADO) {
					compraRepository.delete(c);
				}
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

	public Collection<Vivienda> findViviendaByfiltros(Integer min, Integer max, String numHabs, String zona) {
		Collection<Vivienda> viviendas = vr.getPublicitadasSinComprar();
		viviendas.addAll(vr.getNoPublicitadasSinComprar());
		if(min!= null)
			viviendas.removeAll(vr.getViviendasLtMin(min));
		if(max != null)
			viviendas.removeAll(vr.getViviendasGtMax(max));
//		if(numHabs != null)
//			viviendas.removeAll(vr.getViviendasNotNumHabs(numHabs));
		if(zona != null && !zona.isEmpty())
			viviendas.removeAll(vr.getViviendasNotInZona(zona));
		
		
		return viviendas;
	}
}