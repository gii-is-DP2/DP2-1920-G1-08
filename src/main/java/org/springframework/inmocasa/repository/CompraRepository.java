package org.springframework.inmocasa.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Compra;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Vivienda;

public interface CompraRepository extends JpaRepository<Compra, Integer> {

	// Santi-Alvaro
	@Query("select c from Compra c where c.vivienda.id=?1")
	public Compra findByViviendaId(int id);

	// Alvaro-MiguelEmmanuel

	// Alba-Alejandro
	@Query("select count(c.vivienda) from Compra c where c.estado = 2")
	public Integer getNumOfertas();
	
	@Query("select count(c.vivienda) from Compra c where c.estado = 0")
	public Integer getViviendasCompradas();

	@Query("select vivienda from Compra vivienda where vivienda.cliente = ?1")
	public List<Compra> findAllByCliente(Cliente c);
	
	@Query("select c from Compra c where c.cliente.id = ?1")
	public Collection<Compra> findComprasByCliente(Integer clientId);
	
	@Query("select c from Compra c where c.vivienda.id = ?1")
	public Collection<Compra> findComprasByVivienda(Integer viviendaId);
	
	@Query("select c from Compra c where c.id = ?1")
	public Compra findCompraById(Integer compraId);
	
	@Query("select c from Compra c where c.vivienda.propietario.id = ?1")
	public Collection<Compra> findAllComprasByPropietarioId(Integer propietarioId);

	@Query("select c from Compra c where c.vivienda = ?1 and c.estado <> 0")
	public List<Compra> findAllComprasByVivienda(Vivienda vivienda);
}
