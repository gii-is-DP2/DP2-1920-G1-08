package org.springframework.inmocasa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Visita;

public interface VisitaRepository extends JpaRepository<Visita, Integer> {

	
	//Santi-Alvaro
	
	
	//Alvaro-MiguelEmmanuel
	@Query("select visita from Visita visita where cliente = ?1")
	Collection<Visita> findAllByCliente(Cliente cliente);

	@Query("select visita from Visita visita where cliente = ?1 and fecha > ?2 order by fecha desc ")
	Collection<Visita> findProximasVisitas(Cliente cliente, LocalDateTime fecha);

	@Query("select visita from Visita visita where cliente = ?1 and fecha <= ?2 order by fecha desc ")
	Collection<Visita> findOldVisitas(Cliente cliente, LocalDateTime localDateTime);
	
	//Alba-Alejandro
		
		
}
