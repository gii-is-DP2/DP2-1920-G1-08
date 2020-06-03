package org.springframework.inmocasa.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;

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
import org.springframework.inmocasa.configuration.SecurityConfiguration;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Valoracion;
import org.springframework.inmocasa.model.Visita;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.model.enums.Genero;
import org.springframework.inmocasa.service.ClienteService;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.inmocasa.service.ValoracionService;
import org.springframework.inmocasa.service.VisitaService;
import org.springframework.inmocasa.service.ViviendaService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = VisitaController.class, 
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), 
excludeAutoConfiguration = SecurityConfiguration.class)
@RunWith(SpringRunner.class)
public class VisitaControllerTests {
	private static final int TEST_VIVIENDA_ID_1 = 1;
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UsuarioController usuarioController;

	@MockBean
	private VisitaService visitaService;
	
	@MockBean
	private	ClienteService clienteService;

	@MockBean
	private ViviendaService viviendaService;

	private Visita visita;
	
	private Visita visita2;
	
	private Vivienda vivienda;
	
	private Vivienda vivienda2;
	
	private Cliente cliente;
	
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
		
		cliente = new Cliente();
		cliente.setId(8);
		cliente.setNombre("John");
		cliente.setApellidos("Doe");
		cliente.setDni("46900025N");
		cliente.setGenero(Genero.MASCULINO);
		cliente.setFechaNacimiento(LocalDate.of(1976, 6, 12));
		cliente.setUsername("john123");
		cliente.setPassword("john123");
		
		
	}
	
	
	@WithMockUser(value = "john123", authorities = { "cliente" })
	@Test
	@DisplayName("Prueba en la que se accede al formulario de visita")
	void testCreateFormVisitaOk() throws Exception {
		given(this.visitaService.findById2(1)).willReturn(visita);
		given(this.clienteService.findClienteByUsername("john123")).willReturn(Lists.newArrayList(cliente));
		mockMvc.perform(get("/visita/vivienda/{viviendaId}/new",1)).andExpect(status().isOk()).andExpect(model().attributeExists("visita"))
				.andExpect(view().name("viviendas/visitas/createOrUpdateVisita"));
	}
	

	@WithMockUser(value = "john123", authorities = { "cliente" })
	@Test
	@DisplayName("Prueba en la que se crea una visita")
	void testCreateVisitaOk() throws Exception {
		given(this.visitaService.save(visita)).willReturn(visita);
		mockMvc.perform(get("/visita/save"))
			.andExpect(status().isOk());
	}
	
}
