package org.springframework.inmocasa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.inmocasa.model.Propietario;

public interface PropietarioRepository extends JpaRepository<Propietario, Integer> {

	// Santi-Alvaro
	@Query("select p from Propietario p where p.username = ?1")
	public List<Propietario> findPropietarioByUsername(String username);
	
	
	// Alvaro-MiguelEmmanuel

	@Query("select p from Propietario p where p.username = ?1")
	public Propietario findByUsername(String username);
		
		//Alba-Alejandro
		
}
