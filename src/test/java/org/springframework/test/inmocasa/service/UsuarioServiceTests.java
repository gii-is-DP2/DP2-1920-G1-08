package org.springframework.test.inmocasa.service;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.inmocasa.InmocasaApplication;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Usuario;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.model.enums.Genero;
import org.springframework.inmocasa.service.UsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { InmocasaApplication.class })
public class UsuarioServiceTests {

	@Autowired
	protected UsuarioService usuarioService;

	// HU-023: El usuario se borra sin problemas
	@Test
	void shouldDeleteUsuario() {
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

		this.usuarioService.delete(prop);
		Usuario u = this.usuarioService.findUsuarioByUsername("john123");
		assertThat(u == null);
	}

}
