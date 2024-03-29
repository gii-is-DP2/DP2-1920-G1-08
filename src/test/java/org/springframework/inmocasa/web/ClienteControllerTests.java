package org.springframework.inmocasa.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.ArrayList;

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
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.model.enums.Genero;
import org.springframework.inmocasa.service.ClienteService;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.inmocasa.service.UsuarioService;
import org.springframework.inmocasa.service.ViviendaService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = ClienteController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { InmocasaApplication.class })
public class ClienteControllerTests {

	private static final int TEST_CLIENTE_ID = 1;
	private static final int TEST_ID_VIVIENDA_FAV = 3;

	@MockBean
	private ClienteService clienteService;

	@MockBean
	private PropietarioService propietarioService;

	@MockBean
	private ViviendaService viviendaService;
	
	@MockBean
	private UsuarioService usuarioService;

	@Autowired
	private MockMvc mockMvc;

	private Cliente cliente;
	private Vivienda vivienda;
	private Propietario prop;

	@BeforeEach
	void setup() {

		cliente = new Cliente();
		cliente.setId(TEST_CLIENTE_ID);
		cliente.setNombre("Gregorio");
		cliente.setApellidos("Martín");
		cliente.setDni("12345678D");
		cliente.setEmail("gregorio@gmail.com");
		cliente.setFavoritas(new ArrayList<Vivienda>());
		cliente.setGenero(Genero.MASCULINO);
		cliente.setUsername("gregorio23");
		cliente.setPassword("12345678b");
		cliente.setFechaNacimiento(LocalDate.of(1997, 04, 22));
		cliente.setFavoritas(new ArrayList());

		prop = new Propietario();
		prop.setId(8);
		prop.setNombre("John");
		prop.setApellidos("Doe");
		prop.setDni("46900025N");
		prop.setEsInmobiliaria(false);
		prop.setGenero(Genero.MASCULINO);
		prop.setFechaNacimiento(LocalDate.of(1976, 6, 12));
		prop.setUsername("gilmar");
		prop.setPassword("gilmar");

		vivienda = new Vivienda();
		vivienda.setId(TEST_ID_VIVIENDA_FAV);
		vivienda.setTitulo("Piso en venta en ocho de marzo s/n");
		vivienda.setDireccion("Calle Ocho de Marzo s/n");
		vivienda.setZona("Cerro Amate");
		vivienda.setFechaPublicacion(LocalDate.of(2020, 01, 20));
		vivienda.setPrecio(2260);
		vivienda.setAmueblado(true);
		vivienda.setCaracteristicas("Caracteristicas");
		vivienda.setPropietario(prop);
		vivienda.setHorarioVisita("Martes de 9:00 a 13:00");

	}

	@WithMockUser(username = "admin", password = "admin", authorities = "admin")
	@Test
	void testInitCreationFormSuccess() throws Exception {
		mockMvc.perform(get("/clientes/new"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("cliente"))
		.andExpect(model().attribute("cliente", hasProperty("username")))
		.andExpect(view().name("clientes/registroClientes"));
	}

	@WithMockUser(username = "gregorio23", password = "12345678b", authorities = "cliente")
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		given(this.clienteService.findByUsername(cliente.getUsername())).willReturn(cliente);
		mockMvc.perform(post("/clientes/save")
				.param("id", cliente.getId().toString()).with(csrf()))
			.andExpect(status().is2xxSuccessful());
	}

	@WithMockUser(username = "admin", password = "admin", authorities = "admin")
	@Test
	void testProcessCreationFormHasErrors() throws Exception {
		cliente.setId(20);
		cliente.setUsername("");
		this.clienteService.save(cliente);
		
		Cliente c1 = this.clienteService.findByUsername(cliente.getUsername());
		assertTrue(c1== null);
	}

	// HU-012: Guardar una casa como favorita
	@WithMockUser(value = "gregorio23", authorities = { "cliente" })
	@Test
	@DisplayName("Añadir casa a favoritos")
	void testAniadirFavoritos() throws Exception {
		given(this.clienteService.findByUsername(cliente.getUsername())).willReturn(cliente);
		given(this.clienteService.findViviendaById(TEST_ID_VIVIENDA_FAV)).willReturn(vivienda);
		mockMvc.perform(get("/clientes/{viviendaId}/favoritos", TEST_ID_VIVIENDA_FAV)).andExpect(status().isOk())
				.andExpect(view().name("viviendas/favoritas"));
	}

	@WithMockUser(value = "gregorio23", authorities = { "cliente" })
	@Test
	@DisplayName("Lista de favoritos")
	void testListFavoritos() throws Exception {
		given(this.clienteService.findByUsername(cliente.getUsername())).willReturn(cliente);
		mockMvc.perform(get("/clientes/lista/favoritas")).andExpect(status().isOk())
				.andExpect(view().name("viviendas/favoritas"));
	}

}
