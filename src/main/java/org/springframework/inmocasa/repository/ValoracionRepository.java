package org.springframework.inmocasa.repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.inmocasa.model.Valoracion;
import org.springframework.inmocasa.model.Visita;

public interface ValoracionRepository extends JpaRepository<Valoracion, Integer> {


	// Santi-Alvaro

	// Alvaro-MiguelEmmanuel
	@Query("select valoracion from Valoracion valoracion where valoracion.visita = ?1")
	List<Valoracion> findByVisita(Visita visita);

	// Alba-Alejandro


}
