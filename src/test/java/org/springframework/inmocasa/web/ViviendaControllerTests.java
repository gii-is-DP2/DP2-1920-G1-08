package org.springframework.inmocasa.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.ArrayList;

import org.assertj.core.util.Lists;
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
import org.springframework.inmocasa.model.Compra;
import org.springframework.inmocasa.model.Habitacion;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.model.enums.Estado;
import org.springframework.inmocasa.model.enums.Genero;
import org.springframework.inmocasa.service.AdministradorService;
import org.springframework.inmocasa.service.ClienteService;
import org.springframework.inmocasa.service.CompraService;
import org.springframework.inmocasa.service.DenunciaService;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.inmocasa.service.ViviendaService;
import org.springframework.inmocasa.web.ViviendaController;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = ViviendaController.class, 
	excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), 
	excludeAutoConfiguration = SecurityConfiguration.class)
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { InmocasaApplication.class })
class ViviendaControllerTests {

	private static final int TEST_VIVIENDA_ID_1 = 1;
	private static final int TEST_VIVIENDA_ID_2 = 2;
	private static final String testPropietarioUsername = "propietario1";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ViviendaService viviendaService;

	@MockBean
	private PropietarioService propietarioService;

	@MockBean
	private CompraService compraService;

	@MockBean
	private DenunciaService denunciaService;
		
	@MockBean
	private AdministradorService adminService;
	
	@MockBean
	private ClienteService clienteService;
	
	private Vivienda vivienda;

	private Vivienda vivienda2;

	private Compra compra1;

	private Propietario prop;

	@BeforeEach
	void setup() {
		vivienda = new Vivienda();
		vivienda.setId(TEST_VIVIENDA_ID_1);
		vivienda.setTitulo("Piso en venta en ocho de marzo s/n");
		vivienda.setDireccion("Calle Ocho de Marzo s/n");
		vivienda.setZona("Cerro Amate");
		vivienda.setFechaPublicacion(LocalDate.of(2020, 01, 20));
		vivienda.setPrecio(2260);
		vivienda.setAmueblado(true);
		vivienda.setCaracteristicas("Caracteristicas");
		vivienda.setHorarioVisita("Martes de 9:00 a 13:00");

		vivienda2 = new Vivienda();
		vivienda2.setId(TEST_VIVIENDA_ID_2);
		vivienda2.setTitulo("Piso en venta en ocho de marzo s/n");
		vivienda2.setDireccion("Calle Ocho de Marzo s/n");
		vivienda2.setZona("Cerro Amate");
		vivienda2.setFechaPublicacion(LocalDate.of(2020, 01, 20));
		vivienda2.setPrecio(2260);
		vivienda2.setAmueblado(true);
		vivienda2.setCaracteristicas("Caracteristicas");
		vivienda2.setHorarioVisita("Martes de 9:00 a 13:00");

		prop = new Propietario();
		prop.setId(8);
		prop.setNombre("John");
		prop.setApellidos("Doe");
		prop.setDni("46900025N");
		prop.setEsInmobiliaria(false);
		prop.setGenero(Genero.MASCULINO);
		prop.setFechaNacimiento(LocalDate.of(1976, 6, 12));
		prop.setUsername(testPropietarioUsername);
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

		Compra compra1 = new Compra();
		compra1.setVivienda(vivienda2);
		compra1.setEstado(Estado.ACEPTADO);
		compra1.setPrecioFinal(200);
		vivienda.setPropietario(prop);
		vivienda2.setPropietario(prop);
		compra1.setCliente(clie);

		Habitacion hab = new Habitacion();
		hab.setId(1);
		hab.setNumCamas(1);
		hab.setVivienda(vivienda);
	}

	// HU-02
	@WithMockUser(value = "gilmar", authorities = { "propietario" })
	@Test
	void testListViviendasOfertadasOK() throws Exception {
		mockMvc.perform(get("/viviendas/ofertadas")).andExpect(view().name("viviendas/listaViviendasOferta"))
				.andExpect(status().isOk());
	}

	@WithMockUser(value = "gilmar", authorities = { "propietario" })
	@Test
	void testListViviendasOfertadasNotOK() throws Exception {
		mockMvc.perform(get("/viviendas/ofertadas").with(csrf()).param("estado", "Estado.PENDIENTE"))
				.andExpect(view().name("viviendas/listaViviendasOferta")).andExpect(status().isOk());
	}

	//HU-03 Publicar una nueva vivienda
	@WithMockUser(value = "gilmar", authorities = { "propietario" })
	@Test
	@DisplayName("Creación del formulario de la vivienda")
	void testNewVivienda() throws Exception {
		mockMvc.perform(get("/viviendas/new"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("vivienda"))
			.andExpect(model().attribute("vivienda", hasProperty("propietario")))
			.andExpect(view().name("viviendas/editVivienda"));
	}
	
	@WithMockUser(value = "gilmar", authorities = { "propietario" })
	@Test
	@DisplayName("Creación de la vivienda")
	void testCrearVivienda() throws Exception{
		given(this.propietarioService.findByUsername(testPropietarioUsername)).willReturn(prop);
		mockMvc.perform(post("/viviendas/save")
				.param("id", vivienda.getId().toString()).with(csrf()))
			.andExpect(status().is2xxSuccessful());
	}
	
	// HU-04
	@WithMockUser(value = "gilmar", authorities = { "propietario" })
	@Test
	@DisplayName("Prueba en la que se muestran todas las viviendas")
	void testListViviendasOk() throws Exception {
		mockMvc.perform(get("/viviendas/allNew")).andExpect(view().name("viviendas/listNewViviendas"))
				.andExpect(status().isOk()).andDo(print());
	}
//	@WithMockUser(value = "alejandra", authorities = { "cliente" })
//	@Test
//	void testNewViviendaDenied() throws Exception {
//		mockMvc.perform(get("/viviendas/new"))
//			.andExpect(status().isForbidden());
//	}
	
	@Test
	void testCreateViviendaNegativo() throws Exception {
		vivienda.setId(20);
		vivienda.setCaracteristicas(null);
		this.viviendaService.save(vivienda);
		
		Vivienda res = this.viviendaService.findViviendaById(vivienda.getId()).orElse(null);
		assertThat(res== null);
	}
	
	@WithMockUser(value = "gilmar", authorities = { "propietario" })
	@Test
	void testCreateVivienda() throws Exception {
		vivienda.setId(20);
		this.viviendaService.save(vivienda);
		
		Vivienda res = this.viviendaService.findViviendaById(vivienda.getId()).orElse(null);
		assertThat(res!= null);
	}
	
	// HU-04
	@WithMockUser(value = "gilmar", authorities = { "propietario" })
	@Test
	void testListMisViviendasOk() throws Exception {
		mockMvc.perform(get("/viviendas/mis-viviendas")).andExpect(view().name("viviendas/misViviendas"))
				.andExpect(status().isOk()).andDo(print());
	}

//	@WithMockUser(username = "john123", authorities = { "propietario" })
//	@Test
//	void showViviendaDetails() throws Exception {
//
//		mockMvc.perform(get("/viviendas/{viviendaId}", TEST_VIVIENDA_ID_1)).andExpect(status().isOk())
//				.andExpect(view().name("viviendas/showViviendaDetails"));
//	}

	// HU-008: Filtrar vivienda
	@WithMockUser(value = "inmocasa")
	@Test
	void testshowListViviendaFiltroOk() throws Exception {
		given(this.viviendaService.findViviendaByfiltros(100, 3000, null, "Cerro Amate"))
		.willReturn(Lists.newArrayList(vivienda, new Vivienda()));

		mockMvc.perform(get("/viviendas/allNewFiltros").with(csrf()).param("min", "100").param("max", "3000")
				.param("zona", "Cerro Amate")).andExpect(status().isOk()).andExpect(view().name("viviendas/listNewViviendas"));
	}

	// HU-008: Filtrar vivienda
	@WithMockUser(value = "inmocasa")
	@Test
	void testshowListViviendaFiltroNotOk() throws Exception {
		given(this.viviendaService.findViviendaByfiltros(100, 20, null, null)).willReturn(new ArrayList<Vivienda>());

		mockMvc.perform(get("/viviendas/allNewFiltros").with(csrf()).param("min", "100").param("max", "20"))
				.andExpect(model().attribute("error", "El precio mínimo debe ser menor al precio máximo."))
				.andExpect(status().isOk()).andExpect(view().name("viviendas/listNewViviendas"));
	}
	
	//HU-020: Borrar un anuncio
	@WithMockUser(value = "gilmar", authorities = { "propietario" })
	@Test
	void testDeleteViviendaOk() throws Exception {
		mockMvc.perform(get("/viviendas/delete/{viviendaId}", 1)).andExpect(status().isOk());
	}
	
	//HU-020: Borrar un anuncio (No se borra porque no hay nadie logueado)
	@Test
	void testDeleteViviendaNotOk() throws Exception {
		mockMvc.perform(get("/viviendas/delete/{viviendaId}", 1)).andExpect(status().is4xxClientError());
	}
}