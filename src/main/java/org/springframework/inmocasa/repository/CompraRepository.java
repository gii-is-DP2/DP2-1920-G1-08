package org.springframework.inmocasa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.inmocasa.model.Compra;

public interface CompraRepository extends JpaRepository<Compra, Integer> {

	// Santi-Alvaro
	@Query("select c from Compra c where c.vivienda.id=?1")
	public Compra findByViviendaId(int id);

	// Alvaro-MiguelEmmanuel

	// Alba-Alejandro
	@Query("select count(c.vivienda) from Compra c where c.estado = 3")
	public Integer getNumOfertas();
	
	@Query("select count(c.vivienda) from Compra c where c.estado = 1")
	public Integer getViviendasCompradas();
}
