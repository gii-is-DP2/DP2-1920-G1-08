package org.springframework.inmocasa.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.enums.Genero;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.repository.ClienteRepository;
import org.springframework.inmocasa.repository.PropietarioRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

	ClienteRepository crep;
	AuthoritiesService authoritiesService;
	PropietarioRepository propRepository;

	@Autowired
	public ClienteService(ClienteRepository crep, AuthoritiesService authoritiesService,
			PropietarioRepository propRepository) {
		this.crep = crep;
		this.authoritiesService = authoritiesService;
		this.propRepository = propRepository;
	}

	// Santi-Alvaro
	@Transactional
	public Collection<Cliente> findAll() {
		return crep.findAll();
	}

	public Cliente findClienteByClienteUsername(String username) {
		return crep.findByClienteUsername(username);
	}
	
	public Cliente findClienteById(Integer id) {
		return crep.findClienteById(id);
	}
	
	

	public void saveCliente(@Valid Cliente cliente) {

		crep.save(cliente);

		authoritiesService.saveAuthorities(cliente.getUsername(), "cliente");

	}

	// Alvaro-MiguelEmmanuel
	public List<Cliente> findClienteByUsername(String username) {
		return crep.findByUsername(username);
	}

	
	public Cliente findByUsername(String username) {
		return crep.findClienteByUsername(username);
	}
	
	public Vivienda findViviendaById(Integer viviendaId) {
		return crep.viviendaById(viviendaId);

	}
	
	public void save(Cliente cliente) {
		   crep.save(cliente);
		}
	
	public Boolean esFavorito(List<Vivienda> favoritos, Integer idVivienda) {
		Boolean res = false;
		Integer i = 0;
		Integer tamaño = favoritos.size();
		while(i < tamaño) {
		res = favoritos.get(i).getId() == idVivienda;
		if(res) {
			break;
		}
		i++;
		}
		return res;
	}
	
	//Alba-Alejandro
	


}
