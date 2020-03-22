package org.springframework.inmocasa.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.inmocasa.model.Compra;

public interface CompraRepository extends CrudRepository<Compra, Integer> {

	// Santi-Alvaro
	@Query("select c from Compra c")
    public Collection<Compra> findOfertas();
	// Alvaro-MiguelEmmanuel

	// Alba-Alejandro

}
