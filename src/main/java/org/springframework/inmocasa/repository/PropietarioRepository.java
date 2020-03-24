package org.springframework.inmocasa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.inmocasa.model.Propietario;

public interface PropietarioRepository  extends  CrudRepository<Propietario, Integer>{

	
	//Santi-Alvaro
	
	
		//Alvaro-MiguelEmmanuel
		
	@Query("select p from Propietario p where p.username = ?1")
	public Propietario findByUsername(String username);
		
		//Alba-Alejandro
		
		
}
