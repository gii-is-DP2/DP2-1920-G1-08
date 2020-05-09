package org.springframework.inmocasa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

	
	private ClienteRepository crep;
	

	@Autowired
	public ClienteService(ClienteRepository clienteR) {
		this.crep = clienteR;
	}
	
	//Santi-Alvaro
	
	
	//Alvaro-MiguelEmmanuel
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
	
//	public Cliente findClienteByUsername(String username) {
//	return crep.findByUsername(username);
//}

}
