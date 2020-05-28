package org.springframework.inmocasa.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Compra;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.model.enums.Estado;
import org.springframework.inmocasa.model.enums.Genero;
import org.springframework.stereotype.Service;

import com.paypal.base.rest.APIContext;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ViviendaServiceTests {

	@Autowired
	protected ViviendaService viviendaService;

	@MockBean
	APIContext apiContext;

	// La vivienda se crea y se guarda en el repositorio
	@Test
	void shouldCreateAndSaveVivienda() {
		Collection<Vivienda> viviendas = (Collection<Vivienda>) this.viviendaService.findAll();

		Vivienda vivienda = new Vivienda();
		vivienda.setId(1);
		vivienda.setTitulo("Piso en venta en ocho de marzo s/n");
		vivienda.setDireccion("Calle Ocho de Marzo s/n");
		vivienda.setZona("Cerro Amate");
		vivienda.setFechaPublicacion(LocalDate.of(2020, 01, 20));
		vivienda.setPrecio(2260);
		vivienda.setAmueblado(true);
		vivienda.setCaracteristicas("Caracteristicas");
		vivienda.setHorarioVisita("Martes de 9:00 a 13:00");

		this.viviendaService.save(vivienda);

		assertThat(viviendas.contains(vivienda));

	}

	// La vivienda no se guarda porque ya existe una con el mismo id
	@Test
	void shouldNotCreateAndSaveVivienda() {
		Collection<Vivienda> viviendas = (Collection<Vivienda>) this.viviendaService.findAll();

		Vivienda vivienda = new Vivienda();
		vivienda.setId(1);
		vivienda.setTitulo("Piso en venta en ocho de marzo s/n");
		vivienda.setDireccion("Calle Ocho de Marzo s/n");
		vivienda.setZona("Cerro Amate");
		vivienda.setFechaPublicacion(LocalDate.of(2020, 01, 20));
		vivienda.setPrecio(2260);
		vivienda.setAmueblado(true);
		vivienda.setCaracteristicas("Caracteristicas");
		vivienda.setHorarioVisita("Martes de 9:00 a 13:00");
		this.viviendaService.save(vivienda);

		assertThat(!viviendas.contains(vivienda));

	}

	// Se encuentra la vivienda por el id y esta existe.
	@Test
	void shouldFindViviendaById() {
		Collection<Vivienda> viviendas = (Collection<Vivienda>) this.viviendaService.findAll();
		Vivienda v = this.viviendaService.findViviendaId(1);
		assertThat(viviendas.contains(v));

	}

	// No se encuentra la vivienda porque el id no existe.
	@Test
	void shouldNoFindViviendaByViviendaId() {
		Vivienda v = this.viviendaService.findViviendaId(10);
		assertThat(v == null);
	}
	
	//La vivienda se borra sin problemas
	@Test
	void shouldDeleteVivienda() {
		Collection<Vivienda> todas = this.viviendaService.findAllNewest();
		
		Vivienda vivienda = new Vivienda();
		vivienda.setId(1);
		vivienda.setTitulo("Piso en venta en ocho de marzo s/n");
		vivienda.setDireccion("Calle Ocho de Marzo s/n");
		vivienda.setZona("Cerro Amate");
		vivienda.setFechaPublicacion(LocalDate.of(2020, 01, 20));
		vivienda.setPrecio(2260);
		vivienda.setAmueblado(true);
		
		Propietario prop = new Propietario();
		prop.setId(1);
		prop.setNombre("John");
		prop.setApellidos("Doe");
		prop.setDni("46900025N");
		prop.setEsInmobiliaria(false);
		prop.setGenero(Genero.MASCULINO);
		prop.setFechaNacimiento(LocalDate.of(1976, 6, 12));
		prop.setUsername("john123");
		prop.setPassword("john123");
		
		vivienda.setPropietario(prop);
		
		this.viviendaService.delete(vivienda);
		assertThat(!todas.contains(vivienda));
	}
	
	//La vivienda no se borra porque está comprada
	@Test
	void shoudNotDeleteVivienda() {
		Collection<Vivienda> todas = this.viviendaService.findAllNewest();
		
		Vivienda vivienda2 = new Vivienda();
		vivienda2.setId(2);
		vivienda2.setTitulo("Piso en venta en ocho de marzo s/n");
		vivienda2.setDireccion("Calle Ocho de Marzo s/n");
		vivienda2.setZona("Cerro Amate");
		vivienda2.setFechaPublicacion(LocalDate.of(2020, 01, 20));
		vivienda2.setPrecio(2260);
		vivienda2.setAmueblado(true);
		
		Compra compra = new Compra();
		compra.setVivienda(vivienda2);
		compra.setEstado(Estado.ACEPTADO);
		compra.setPrecioFinal(200);
		
		Propietario prop = new Propietario();
		prop.setId(1);
		prop.setNombre("John");
		prop.setApellidos("Doe");
		prop.setDni("46900025N");
		prop.setEsInmobiliaria(false);
		prop.setGenero(Genero.MASCULINO);
		prop.setFechaNacimiento(LocalDate.of(1976, 6, 12));
		prop.setUsername("john123");
		prop.setPassword("john123");
		
		Cliente clie = new Cliente();
		clie.setId(8);
		clie.setNombre("John");
		clie.setApellidos("Doe");
		clie.setDni("46900025N");
		clie.setGenero(Genero.MASCULINO);
		clie.setFechaNacimiento(LocalDate.of(1976, 6, 12));
		clie.setUsername("john123");
		clie.setPassword("john123");
		
		vivienda2.setPropietario(prop);
		compra.setCliente(clie);
		
		this.viviendaService.delete(vivienda2);
		assertThat(todas.contains(vivienda2));		
	}
	
}
