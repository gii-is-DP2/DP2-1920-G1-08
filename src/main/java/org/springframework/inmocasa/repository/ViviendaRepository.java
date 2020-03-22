package org.springframework.inmocasa.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.inmocasa.model.Vivienda;

public interface ViviendaRepository extends JpaRepository<Vivienda, Integer> {

	
	
	//Santi-Alvaro
	
	
	//Alvaro-MiguelEmmanuel
	@Query("select v from Vivienda v order by fechaPublicacion desc")
	public Collection<Vivienda> findAllNewest();
	
	//Alba-Alejandro
	@Query("select v from Vivienda v where v.id = ?1")
	public Vivienda findViviendaById(Integer viviendaId);
	
	@Query("select c.vivienda from Compra c")
	public Collection<Vivienda> getViviendasCompradas();
	
	
}
