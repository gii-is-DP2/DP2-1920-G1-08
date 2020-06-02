package org.springframework.inmocasa.repository;


import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Valoracion;
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
	
	@Query("select v from Visita v where v.vivienda.id = ?1")
	Collection<Visita> findVisitasByVivienda(Integer visitaId);
	
	@Query("select v from Visita v where v.cliente.id = ?1")
	Collection<Visita> findVisitasByCliente(Integer clienteId);
	
	@Query("select v from Valoracion v where v.visita.id = ?1")
	Collection<Valoracion> findValoracionesByVisita(Integer visitaId);

	@Query("select visita from Visita visita where visita.id = ?1")
	Visita findById2(Integer id);		
		
}
