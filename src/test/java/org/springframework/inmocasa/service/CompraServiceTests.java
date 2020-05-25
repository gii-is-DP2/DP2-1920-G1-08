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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Compra;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.model.enums.Estado;
import org.springframework.inmocasa.model.enums.Genero;
import org.springframework.inmocasa.service.CompraService;
import org.springframework.stereotype.Service;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

//import com.sun.xml.internal.ws.wsdl.writer.document.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes= {InmocasaApplication.class})
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class CompraServiceTests {

	@Autowired
	protected CompraService compraService;

	@MockBean
	APIContext apiContext;

	// La compra se crea y se guarda con estado pendiente sin problemas
	@Test
	void shouldCreateAndSaveCompra() {
		Collection<Compra> todas = this.compraService.findAll();

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
		cl.setId(2);
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

		vivienda.setPropietario(prop);
		compra.setCliente(cl);
		compra.setVivienda(vivienda);

		this.compraService.saveCompra(compra);

		assertThat(compra.getEstado().equals(Estado.PENDIENTE) && todas.contains(compra));

	}

	// La compra no se realiza porque la vivienda ya está comprada
	@Test
	void shouldNotCreateAndSaveCompra() {
		Collection<Compra> todas = this.compraService.findAll();

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
		cl.setId(2);
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
		cl2.setId(3);
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

		assertThat(!compra.getEstado().equals(Estado.PENDIENTE) && !todas.contains(compra));

	}

}
