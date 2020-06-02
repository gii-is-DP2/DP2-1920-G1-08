package org.springframework.inmocasa.web;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.inmocasa.InmocasaApplication;
import org.springframework.inmocasa.configuration.SecurityConfiguration;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Mensaje;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.model.enums.Genero;
import org.springframework.inmocasa.service.ClienteService;
import org.springframework.inmocasa.service.MensajeService;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.inmocasa.web.MensajeController;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.paypal.base.rest.APIContext;

@WebMvcTest(controllers = MensajeController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
@RunWith(SpringRunner.class)
public class MensajeControllerTests {

	private static final int TEST_MENSAJE_ID = 1;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MensajeService mensajeService;
	@MockBean
	APIContext apiContext;

	@MockBean
	private PropietarioService propietarioService;

	@MockBean
	private ClienteService clienteService;

	private Mensaje mensaje;

	private Propietario prop;

	@BeforeEach
	void setup() {
		mensaje = new Mensaje();
		mensaje.setId(TEST_MENSAJE_ID);
		mensaje.setAsunto("Hola");
		mensaje.setCuerpo("CASA");

		prop = new Propietario();
		prop.setId(8);
		prop.setNombre("John");
		prop.setApellidos("Doe");
		prop.setDni("46900025N");
		prop.setEsInmobiliaria(false);
		prop.setGenero(Genero.MASCULINO);
		prop.setFechaNacimiento(LocalDate.of(1976, 6, 12));
		prop.setUsername("john123");
		prop.setPassword("john123");

		Cliente clie = new Cliente();
		clie.setId(7);
		clie.setNombre("pepe");
		clie.setApellidos("Doe");
		clie.setDni("46900025N");
		clie.setGenero(Genero.MASCULINO);
		clie.setFechaNacimiento(LocalDate.of(1976, 6, 12));
		clie.setUsername("pepe");
		clie.setPassword("pepe");

		mensaje.setClient(clie);
		mensaje.setProp(prop);
		mensaje.setEmisorId(prop.getId());
		mensaje.setReceptorId(clie.getId());
	}

	// HU-17
	@WithMockUser(username = "john123")
	@Test
	@DisplayName("Inicio envío mensaje")
	void testInitCreationForm() throws Exception {
		given(this.propietarioService.findByUsername("john123")).willReturn(prop);		mockMvc.perform(get("/mensajes/new").with(csrf())).andExpect(status().isOk())
				.andExpect(view().name("mensajes/editMensaje"));
	}

	@WithMockUser(username = "john123", authorities = { "propietario" })
	@Test
	@DisplayName("Se envía mensaje")
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/mensajes/save").with(csrf()).param("asunto", "Hello").param("cuerpo", "Bonjour"))
				.andExpect(status().isOk()).andExpect(view().name("mensajes/misMensajes"));
	}
	@WithMockUser(username = "john123", authorities = { "propietario" })
	@Test
	@DisplayName("No se envía mensaje")
	void testProcessCreationHasError() throws Exception{
		Mensaje mensaje1= new Mensaje();
		mensaje1.setAsunto("Mensaje");
		mensaje1.setCuerpo("Prueba");
		mensaje1.setProp(prop);
		
		mensajeService.save(mensaje);
		Collection<Mensaje> res = this.mensajeService.findMensajeByEmisorId(mensaje1.getEmisorId());
		assertTrue(res.isEmpty());
		
	}
	

	@WithMockUser(value = "john123", authorities = { "propietario" })
	@Test
	@DisplayName("Lista mensajes recibidos")
	void testListMensajesRecibidosOK() throws Exception {
		mockMvc.perform(get("/mensajes/mensajes-recibidos")).andExpect(view().name("mensajes/misMensajes"))
				.andExpect(status().isOk());
	}

	@WithMockUser(value = "john123", authorities = { "propietario" })
	@Test
	@DisplayName("Lista mensajes aceptados")
	void testListMensajesEnviados() throws Exception {
		mockMvc.perform(get("/mensajes/mensajes-enviados")).andExpect(view().name("mensajes/misMensajes"))
				.andExpect(status().isOk());
	}
}
