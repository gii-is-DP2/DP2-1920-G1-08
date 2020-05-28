package org.springframework.inmocasa.web;

import java.time.LocalDate;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.BeforeEach;
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

@WebMvcTest(controllers = MensajeController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
@RunWith(SpringRunner.class)
public class MensajeControllerTests {

	private static final int TEST_MENSAJE_ID = 1;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MensajeService mensajeService;

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
		clie.setNombre("John");
		clie.setApellidos("Doe");
		clie.setDni("46900025N");
		clie.setGenero(Genero.MASCULINO);
		clie.setFechaNacimiento(LocalDate.of(1976, 6, 12));
		clie.setUsername("john123");
		clie.setPassword("john123");

		mensaje.setClient(clie);
		mensaje.setProp(prop);
		mensaje.setEmisorId(prop.getId());
		mensaje.setReceptorId(clie.getId());
	}

	// HU-17
	@WithMockUser(username = "gilmar")
	@Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/mensajes/new")).andExpect(status().isOk()).andExpect(view().name("mensajes/editMensaje"))
				.andExpect(model().attributeExists("mensaje"));
	}

	@WithMockUser(username = "gilmar", authorities = { "propietario" })
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/mensajes/new").param("asunto", "Hello").param("cuerpo", "Bonjour"))
				.andExpect(status().isOk()).andExpect(view().name("mensajes/editMensaje"));
	}

	@WithMockUser(value = "gilmar", authorities = { "propietario" })
	@Test
	void testListMensajesRecibidosOK() throws Exception {
		mockMvc.perform(get("/mensajes/mensajes-recibidos")).andExpect(view().name("mensajes/misMensajes"))
				.andExpect(status().isOk());
	}

	@WithMockUser(value = "gilmar", authorities = { "propietario" })
	@Test
	void testListMensajesEnviados() throws Exception {
		mockMvc.perform(get("/mensajes/mensajes-enviados")).andExpect(view().name("mensajes/misMensajes"))
				.andExpect(status().isOk());
	}
}
