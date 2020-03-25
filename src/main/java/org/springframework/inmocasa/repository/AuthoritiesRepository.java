package org.springframework.inmocasa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.inmocasa.model.Authorities;



public interface AuthoritiesRepository extends JpaRepository<Authorities, String>{
	
}
