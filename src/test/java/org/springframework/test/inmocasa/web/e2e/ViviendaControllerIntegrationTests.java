package org.springframework.test.inmocasa.web.e2e;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Compra;
import org.springframework.inmocasa.model.Habitacion;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.model.enums.Estado;
import org.springframework.inmocasa.model.enums.Genero;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class ViviendaControllerIntegrationTests {

	private static final int TEST_VIVIENDA_ID_1 = 1;
	private static final int TEST_VIVIENDA_ID_2 = 2;
	private static final String testPropietarioUsername = "propietario1";

	@Autowired
	private MockMvc mockMvc;

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

	// HU-008: Filtrar por precio
	@WithMockUser(value = "inmocasa")
	@Test
	void testshowListViviendaPrecioOk() throws Exception {
		mockMvc.perform(get("/viviendas/allNew").with(csrf()).param("precioMin", "100").param("precioMax", "10000"))
				.andExpect(status().isOk()).andExpect(view().name("viviendas/listNewViviendas"));
	}

	// HU-008: Filtrar por precio (No hay viviendas entre el rango de precio por lo
	// que se muestra un mensaje de error)
	@WithMockUser(value = "inmocasa")
	@Test
	void testshowListViviendaNotPrecioOk() throws Exception {
		mockMvc.perform(get("/viviendas/allNew").with(csrf()).param("precioMin", "10").param("precioMax", "20"))
				.andExpect(model().attribute("error", "No se han encontrado viviendas en este rango de precio"))
				.andExpect(status().isOk()).andExpect(view().name("viviendas/listNewViviendas"));
	}

	// HU-008: Filtrar por zona
	@WithMockUser(value = "inmocasa")
	@Test
	void testshowListViviendaZonaOk() throws Exception {
		mockMvc.perform(get("/viviendas/allNew").with(csrf()).param("zona", "Cerro Amate")).andExpect(status().isOk())
				.andExpect(view().name("viviendas/listNewViviendas"));
	}

	// HU-008: Filtrar por zona (No hay viviendas en la zona seleccionada)
	@WithMockUser(value = "inmocasa")
	@Test
	void testshowListViviendaZonaNotOk() throws Exception {
		mockMvc.perform(get("/viviendas/allNew").with(csrf()).param("zona", "San Jerónimo"))
				.andExpect(model().attribute("error", "No se han encontrado viviendas en esta zona"))
				.andExpect(status().isOk()).andExpect(view().name("viviendas/listNewViviendas"));
	}

	// HU-008: Filtrar por habitaciones
	@WithMockUser(value = "inmocasa")
	@Test
	void testshowListViviendaHabitacionesOk() throws Exception {
		mockMvc.perform(get("/viviendas/allNew").with(csrf()).param("numhabitacion", "1")).andExpect(status().isOk())
				.andExpect(view().name("viviendas/listNewViviendas"));
	}

	// HU-008: Filtrar por Habitaciones (No hay viviendas con el número de
	// habitaciones especificado)
	@WithMockUser(value = "inmocasa")
	@Test
	void testshowListViviendaHabitacionesNotOk() throws Exception {
		mockMvc.perform(get("/viviendas/allNew").with(csrf()).param("numhabitacion", "4"))
				.andExpect(model().attribute("error", "No se han encontrado viviendas con este número de habitaciones"))
				.andExpect(status().isOk()).andExpect(view().name("viviendas/listNewViviendas"));
	}

	// HU-020: Borrar un anuncio
	@WithMockUser(value = "gilmar", authorities = { "propietario" })
	@Test
	void testDeleteViviendaOk() throws Exception {
		mockMvc.perform(get("/viviendas/delete/{viviendaId}", 1)).andExpect(status().isOk());
	}

	// HU-020: Borrar un anuncio (No se borra porque no hay nadie logueado)
	@Test
	void testDeleteViviendaNotOk() throws Exception {
		mockMvc.perform(get("/viviendas/delete/{viviendaId}", 1)).andExpect(status().is4xxClientError());
	}
}
