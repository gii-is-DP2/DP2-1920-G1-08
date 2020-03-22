package org.springframework.inmocasa.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.inmocasa.model.Vivienda;

public interface ViviendaRepository extends  CrudRepository<Vivienda, Integer> {

	
	
	//Santi-Alvaro
	@Query("select v from Vivienda v where v.id=?1")
	public Vivienda findById(int id);

	
	//Alvaro-MiguelEmmanuel
	@Query("select v from Vivienda v order by v.fechaPublicacion desc")
	public Collection<Vivienda> findAllNewest();
	
	//Alba-Alejandro
		
		
}
