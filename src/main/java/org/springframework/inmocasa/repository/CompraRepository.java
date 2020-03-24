package org.springframework.inmocasa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.inmocasa.model.Compra;

public interface CompraRepository extends CrudRepository<Compra, Integer> {

	// Santi-Alvaro
	@Query("select c from Compra c where c.vivienda.id=?1")
	public Compra findByViviendaId(int id);
	// Alvaro-MiguelEmmanuel

	// Alba-Alejandro

}
