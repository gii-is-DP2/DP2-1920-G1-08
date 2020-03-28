package org.springframework.test.inmocasa.web;


import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;

import org.assertj.core.util.Lists;
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
import org.springframework.inmocasa.model.Compra;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.model.enums.Estado;
import org.springframework.inmocasa.model.enums.Genero;
import org.springframework.inmocasa.service.CompraService;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.inmocasa.service.ViviendaService;
import org.springframework.inmocasa.web.ViviendaController;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javafx.beans.binding.When;

@WebMvcTest(controllers=ViviendaController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
@RunWith(SpringRunner.class)
@ContextConfiguration(classes={InmocasaApplication.class})
class ViviendaControllerTests {
	
	private static final int TEST_VIVIENDA_ID_1 = 1;
	private static final int TEST_VIVIENDA_ID_2 = 2;

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ViviendaService viviendaService;
	
	@MockBean
	private PropietarioService propietarioService;
	
	@MockBean
	private CompraService compraService;
	
	private Vivienda vivienda;
	
	private Vivienda vivienda2;
		
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
		
		Compra compra = new Compra();
		compra.setVivienda(vivienda2);
		compra.setEstado(Estado.ACEPTADO);
		compra.setPrecioFinal(200);
				
		Propietario prop = new Propietario();
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
		clie.setId(8);
		clie.setNombre("John");
		clie.setApellidos("Doe");
		clie.setDni("46900025N");
		clie.setGenero(Genero.MASCULINO);
		clie.setFechaNacimiento(LocalDate.of(1976, 6, 12));
		clie.setUsername("john123");
		clie.setPassword("john123");
		
		vivienda.setPropietario(prop);
		vivienda2.setPropietario(prop);
		compra.setCliente(clie);
	}
		
	// Se muestran las viviendas dentro del rango de precio
	@WithMockUser(value = "spring")
	@Test
	void testshowListViviendaOk() throws Exception {
		given(this.viviendaService.findViviendaByPrecio(100, 10000)).willReturn(Lists.newArrayList(vivienda, new Vivienda()));

		
		mockMvc.perform(get("/viviendas/allNew").with(csrf())
				.param("precioMin", "100")
				.param("precioMax", "10000"))
		.andExpect(status().isOk())
		.andExpect(view().name("viviendas/listNewViviendas"));
	}
	
	// No hay viviendas entre el rango de precio por lo que se muestra un mensaje de error
	@WithMockUser(value = "spring")
	@Test
	void testshowListViviendaNotOk() throws Exception {
		given(this.viviendaService.findViviendaByPrecio(10, 20)).willReturn(Lists.newArrayList());
		
		mockMvc.perform(get("/viviendas/allNew").with(csrf())
				.param("precioMin", "10")
				.param("precioMax", "20"))
		.andExpect(model().attribute("error", "No se han encontrado viviendas en este rango de precio"))
		.andExpect(status().isOk())
		.andExpect(view().name("viviendas/listNewViviendas"));
	}
	
	
}
