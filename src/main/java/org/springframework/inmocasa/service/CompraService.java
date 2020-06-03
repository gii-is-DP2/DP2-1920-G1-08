package org.springframework.inmocasa.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Compra;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.enums.Estado;
import org.springframework.inmocasa.repository.CompraRepository;
import org.springframework.stereotype.Service;

@Service
public class CompraService {	
	CompraRepository cr;
	
	//Santi-Alvaro
	@Autowired
	public CompraService(CompraRepository cr) {
		super();
		this.cr = cr;
	}

	// Santi-Alvaro
	public Collection<Compra> findAll() {
		return cr.findAll();
	}

	public Compra findCompraByViviendaId(int viviendaId) {
		// TODO Auto-generated method stub
		return cr.findByViviendaId(viviendaId);
	}

	public Compra save(Compra compras) {
		// TODO Auto-generated method stub
		return cr.save(compras);
	}

	public void deleteById(Integer compraId ) {
		// TODO Auto-generated method stub
		cr.deleteById(compraId);
	}
	
	//Alvaro-MiguelEmmanuel
	public List<Compra> findAllFromCliente(Cliente c){
		return cr.findAllByCliente(c);
	}
	
	//Alba-Alejandro
	
	public Compra findCompraById(Integer compraId) {
		return cr.findCompraById(compraId);
	}
	
	public void saveCompra(Compra compra) {		
		compra.setEstado(Estado.PENDIENTE);
		cr.save(compra);
	}
	
	public Integer getNumOfertas() {
		return cr.getNumOfertas();
	}
	
	public Integer getViviendasCompradas() {
		return cr.getViviendasCompradas();
	}
	
	public Collection<Compra> getAllComprasByPropietarioId(Integer propietarioId){
		return cr.findAllComprasByPropietarioId(propietarioId);
	}

}
