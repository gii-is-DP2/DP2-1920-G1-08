package org.springframework.inmocasa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.inmocasa.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	//Santi-Alvaro
	
	
	//Alvaro-MiguelEmmanuel
	
	
	//Alba-Alejandro
	@Query("select c from Cliente c where c.username = ?1")
	public Cliente findByUsername(String username);
	
}
