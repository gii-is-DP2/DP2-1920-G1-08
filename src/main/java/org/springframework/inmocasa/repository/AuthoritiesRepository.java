package org.springframework.inmocasa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.inmocasa.model.Authorities;



public interface AuthoritiesRepository extends  CrudRepository<Authorities, String>{
	
}
