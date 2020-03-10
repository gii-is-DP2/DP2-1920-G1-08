package org.springframework.inmocasa.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.inmocasa.model.Vivienda;

public interface ViviendaRepository extends  CrudRepository<Vivienda, Integer> {

	
	
	//Santi-Alvaro
	
	
	//Alvaro-MiguelEmmanuel
	@Query("select * from Vivienda ordered by fechaPublicacion desc")
	public Collection<Vivienda> findAllNewest();
	
	//Alba-Alejandro
		
		
}
