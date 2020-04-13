package org.springframework.inmocasa.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Mensaje;
import org.springframework.inmocasa.model.Propietario;

public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {

	// Santi-Alvaro

	@Query("select m from Mensaje m order by asunto desc")
	public Collection<Mensaje> findAllMensajes();
	
	@Query("select m from Mensaje m where m.emisor = ?1")
	public Collection<Mensaje> findByEmisor(Cliente emisor);

	@Query("select m from Mensaje m where m.receptor = ?1")
	public Collection<Mensaje> findByReceptor(Propietario receptor);
	// Alvaro-MiguelEmmanuel

	// Alba-Alejandro

}
