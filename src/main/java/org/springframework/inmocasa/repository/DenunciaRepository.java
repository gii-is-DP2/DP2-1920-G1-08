package org.springframework.inmocasa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.inmocasa.model.Denuncia;
import org.springframework.inmocasa.model.Vivienda;

public interface DenunciaRepository extends JpaRepository<Denuncia, Integer> {

	@Query("select v from Vivienda v where v.id = ?1")
	public Vivienda findViviendaById(Integer viviendaId);
}
