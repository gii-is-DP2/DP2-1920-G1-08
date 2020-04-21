package org.springframework.inmocasa.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.inmocasa.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	

	//Santi-Alvaro
	@Query("select c from Cliente c where c.username = ?1")
	public Cliente findByClienteUsername(String username);
	
	
	//Alvaro-MiguelEmmanuel
	@Query("select c from Cliente c where username = ?1")
	public List<Cliente> findByUsername(String username);
	
	//Alba-Alejandro
}
