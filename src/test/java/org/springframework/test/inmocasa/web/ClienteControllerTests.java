package org.springframework.test.inmocasa.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
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
import org.springframework.inmocasa.service.ViviendaService;
import org.springframework.inmocasa.web.ClienteController;
import org.springframework.inmocasa.web.ViviendaController;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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
		mockMvc.perform(get("/clientes/new")).andExpect(status().isOk()).andExpect(model().attributeExists("cliente"))
				.andExpect(view().name("clientes/registroClientes"));
	}
	

	@WithMockUser(username = "admin", password = "admin", authorities = "admin")
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/clientes/save")
				.param("nombre", "test1")
				.param("genero", "Genero.MASCULINO").with(csrf())
				.param("username", "gregorio")
				.param("password", "gregorio1")).andExpect(status().isOk())
				.andExpect(view().name("clientes/registroClientes"));
	}
	
	
	@WithMockUser(username = "admin", password = "admin", authorities = "admin")
	@Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/clientes/save").with(csrf())
				.param("nombre", "189839")
				.param("genero", "MASCULINO").with(csrf())
				.param("username", "gregorio")
				.param("password", "gregorio1")).andExpect(status().isOk()).andExpect(model().attributeHasErrors("cliente"))
				.andExpect(view().name("clientes/registroClientes"));
	}
	
	//HU-012: Guardar una casa como favorita
//	@WithMockUser(value = "alejandra", authorities = { "cliente" })
//	@Test
//	void testAniadirFavoritos() throws Exception{
//		
//		mockMvc.perform(get("/clientes/{viviendaId}/favoritos", TEST_ID_VIVIENDA_FAV))
//			.andExpect(status().isOk())
//			.andExpect(model().attributeExists("clientes"));
////			.andExpect(view().name("viviendas/listNewViviendas"));
//	}
	
//	//No lo hace bien
//	@WithMockUser(username = "gilmar", password = "gilmar", authorities = "propietario")
//	@Test
//	void testAniadirFavoritosPropietarioFail() throws Exception{
//		
//		mockMvc.perform(get("/clientes/{viviendaId}/favoritos", TEST_ID_VIVIENDA_FAV))
//			.andExpect(status().isForbidden());
//	}


}
