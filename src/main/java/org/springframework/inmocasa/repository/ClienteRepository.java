package org.springframework.inmocasa.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Vivienda;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	

	

	//Santi-Alvaro
	@Query("select c from Cliente c where c.username = ?1")
	public Cliente findByClienteUsername(String username);
	
	@Query("select c from Cliente c where c.id = ?1")
	public Cliente findClienteById(Integer clienteId);
	
	
	//Alvaro-MiguelEmmanuel
	@Query("select c from Cliente c where username = ?1")
	public List<Cliente> findByUsername(String username);
	
	@Query("select c from Cliente c where c.username = ?1")
	public Cliente findClienteByUsername(String username);
	
	@Query("select v from Vivienda v where v.id = ?1")
	public Vivienda viviendaById(Integer viviendaId);
	
	//Alba-Alejandro
	
//	@Query("select c from Cliente c where username = ?1")
//	public Cliente findByUsername(String username);
}
