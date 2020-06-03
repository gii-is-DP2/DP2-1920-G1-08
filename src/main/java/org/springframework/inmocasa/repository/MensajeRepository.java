package org.springframework.inmocasa.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.inmocasa.model.Mensaje;

public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {

	// Santi-Alvaro

	@Query("select m from Mensaje m order by asunto desc")
	public Collection<Mensaje> findAllMensajes();

	@Query("select m from Mensaje m where m.emisorId = ?1")
	public Collection<Mensaje> findByEmisorId(Integer emisor);

	@Query("select m from Mensaje m where m.receptorId = ?1")
	public Collection<Mensaje> findByReceptorId(Integer receptor);

	// Alvaro-MiguelEmmanuel

	// Alba-Alejandro
	@Query("select m from Mensaje m where m.receptorId = ?1 or m.emisorId = ?1")
	public Collection<Mensaje> findMensajesByUserId(Integer userId);

}
