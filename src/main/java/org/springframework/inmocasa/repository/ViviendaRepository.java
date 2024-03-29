package org.springframework.inmocasa.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.inmocasa.model.Habitacion;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Vivienda;

public interface ViviendaRepository extends JpaRepository<Vivienda, Integer> {

	
	
	//Santi-Alvaro
    @Query("select v from Vivienda v where v.id=?1")
    public Vivienda ViviendaById(int id);

	
	//Alvaro-MiguelEmmanuel
	@Query("select v from Vivienda v order by fechaPublicacion desc")
	public Collection<Vivienda> findAllNewest();
	
	@Query("select distinct v.zona from Vivienda v ")
	public Collection<String> findAllZonas();
	
	//Alba-Alejandro
	@Query("select v from Vivienda v where v.id = ?1")
	public Vivienda findViviendaById(Integer viviendaId);
	
	@Query("select c.vivienda from Compra c where c.estado=0")
	public Collection<Vivienda> getViviendasCompradas();
	
	@Query("select h from Habitacion h where h.vivienda.id = ?1")
	public Collection<Habitacion> getHabitacionesVivienda(Integer viviendaId);

	@Query("select v from Vivienda v where v.propietario = ?1")
	public List<Vivienda> findAllByPropietario(Propietario p);	

	@Query("select v from Vivienda v where v.precio between :min and :max order by fechaPublicacion desc")
	public Collection<Vivienda> findViviendaByPrecio(@Param("min") Integer min, @Param("max") Integer max);	
	
	@Query("select min(precio) from Vivienda")
	public Integer precioMinViviendas();
	
	@Query("select max(precio) from Vivienda")
	public Integer precioMaxViviendas();
	
	@Query("select v from Vivienda v where v.zona = ?1 order by fechaPublicacion desc")
	public Collection<Vivienda> findViviendaByZona(String zona);
		
	@Query("select h from Habitacion h where h.vivienda.id = ?1")
	public Collection<Habitacion> findHabitacionesByVivienda(Integer vivId);
	
	@Query("select count(id) from Habitacion h where h.vivienda.id=?1")
	public Integer findNumHabitacionesByVivienda(Integer vivId);
	
	@Query("select v from Vivienda v where v.publicitado = 1 order by fechaPublicacion desc")
	public Collection<Vivienda> getPublicitadas();
	
	@Query("select v from Vivienda v where v.publicitado = 1 and v not in (select c.vivienda from Compra c where c.estado = 0) order by fechaPublicacion desc")
	public Collection<Vivienda> getPublicitadasSinComprar();
	
	@Query("select v from Vivienda v where v.publicitado = 0 and v not in (select c.vivienda from Compra c where c.estado = 0) order by fechaPublicacion desc")
	public Collection<Vivienda> getNoPublicitadasSinComprar();
	
	@Query("select v from Vivienda v where v.publicitado = 0 order by fechaPublicacion desc")
	public Collection<Vivienda> getNOPublicitadas();

	@Query("select v from Vivienda v where v.propietario.id = ?1")
	public Collection<Vivienda> findViviendasByPropietario(Integer propId);

	@Query("select v from Vivienda v where v.precio < ?1")
	public Collection<Vivienda> getViviendasLtMin(Integer min);

	@Query("select v from Vivienda v where v.precio > ?1")
	public Collection<Vivienda> getViviendasGtMax(Integer max);

//	@Query("select v from Vivienda v where v.precio < ?1")
//	public Collection<Vivienda> getViviendasNotNumHabs(Integer numHabs);

	@Query("select v from Vivienda v where v.zona <> ?1")
	public Collection<Vivienda> getViviendasNotInZona(String zona);
}
