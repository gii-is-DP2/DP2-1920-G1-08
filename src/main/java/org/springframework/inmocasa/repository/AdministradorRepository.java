package org.springframework.inmocasa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.inmocasa.model.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Integer>{

	
	//Alba-Alejandro
	@Query("select a from Administrador a where a.username = ?1")
	public Administrador findByUsername(String username);
}
