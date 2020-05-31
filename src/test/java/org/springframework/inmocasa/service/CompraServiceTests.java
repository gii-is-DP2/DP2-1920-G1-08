package org.springframework.inmocasa.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

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

//import com.sun.xml.internal.ws.wsdl.writer.document.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class CompraServiceTests {

	@Autowired
	protected CompraService compraService;

	@MockBean
	APIContext apiContext;

	// La compra se crea y se guarda con el estado pendiente sin problemas
	@Test
	void shouldCreateAndSaveCompra() {

		Vivienda vivienda = new Vivienda();
		vivienda.setId(1);
		vivienda.setTitulo("Piso en venta en ocho de marzo s/n");
		vivienda.setDireccion("Calle Ocho de Marzo s/n");
		vivienda.setZona("Cerro Amate");
		vivienda.setFechaPublicacion(LocalDate.of(2020, 01, 20));
		vivienda.setPrecio(2260);

		Propietario prop = new Propietario();
		prop.setId(1);
		prop.setNombre("John");
		prop.setApellidos("Doe");
		prop.setDni("46900025N");
		prop.setEsInmobiliaria(false);
		prop.setGenero(Genero.MASCULINO);
		prop.setUsername("john123");
		prop.setPassword("john123");

		Cliente cl = new Cliente();
		cl.setId(8);
		cl.setNombre("Antonio");
		cl.setApellidos("Fernandez");
		cl.setDni("46900025A");
		cl.setGenero(Genero.MASCULINO);
		cl.setUsername("ant1978");
		cl.setPassword("ant1978");
		
		Compra compra = new Compra();
		compra.setId(1);
		compra.setPrecioFinal(500);

		vivienda.setPropietario(prop);
		compra.setCliente(cl);
		compra.setVivienda(vivienda);

		this.compraService.saveCompra(compra);

		assertEquals(compra.getEstado().equals(Estado.PENDIENTE), true);

	}

	// La compra se rechaza directamente porque la vivienda ya está comprada
	@Test
	void shouldNotCreateAndSaveCompra() {

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

		Cliente cl = new Cliente();
		cl.setId(8);
		cl.setNombre("Antonio");
		cl.setApellidos("Fernandez");
		cl.setDni("46900025A");
		cl.setGenero(Genero.MASCULINO);
		cl.setFechaNacimiento(LocalDate.of(1978, 10, 12));
		cl.setUsername("ant1978");
		cl.setPassword("ant1978");

		Compra compra = new Compra();
		compra.setId(1);
		compra.setPrecioFinal(500);
		compra.setEstado(Estado.ACEPTADO);

		vivienda.setPropietario(prop);
		compra.setCliente(cl);
		compra.setVivienda(vivienda);

		Cliente cl2 = new Cliente();
		cl2.setId(9);
		cl2.setNombre("Juan");
		cl2.setApellidos("Fernandez");
		cl2.setDni("46900025E");
		cl2.setGenero(Genero.MASCULINO);
		cl2.setFechaNacimiento(LocalDate.of(1978, 10, 12));
		cl2.setUsername("juan1978");
		cl2.setPassword("juan1978");

		Compra compra2 = new Compra();
		compra2.setId(2);
		compra2.setPrecioFinal(1000);
		compra2.setCliente(cl2);
		compra2.setVivienda(vivienda);

		this.compraService.saveCompra(compra2);

		assertEquals(compra2.getEstado().equals(Estado.RECHAZADO), false); 

	}

	// Se encuentran la compra por el id de la vivienda y esta existe
	@Test
	void shouldFindCompraByViviendaId() {
		Collection<Compra> todas = this.compraService.findAll();
		Compra c = this.compraService.findCompraByViviendaId(1);
		assertTrue(todas.contains(c));

	}

	// No se encuentra la compra porque esa vivienda no tiene oferta
	@Test
	void shouldNoFindCompraByViviendaId() {
		Compra c = this.compraService.findCompraByViviendaId(2);
		assertTrue(c == null);
	}
}
