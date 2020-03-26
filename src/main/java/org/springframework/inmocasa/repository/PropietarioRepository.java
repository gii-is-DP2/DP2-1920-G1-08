package org.springframework.inmocasa.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.inmocasa.model.Propietario;

public interface PropietarioRepository extends CrudRepository<Propietario, Integer> {

	// Santi-Alvaro

//	@Query("SELECT DISTINCT apellidos FROM Propietario propietario  WHERE propietario.apellidos LIKE apellidos")
//	public Collection<Propietario> findByLastName(@Param("apellidos") String apellidos);

	// Alvaro-MiguelEmmanuel

	// Alba-Alejandro

}
