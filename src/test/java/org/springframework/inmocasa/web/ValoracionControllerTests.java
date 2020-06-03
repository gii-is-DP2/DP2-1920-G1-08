package org.springframework.inmocasa.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.BDDMockito.given;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

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
import org.springframework.inmocasa.model.Valoracion;
import org.springframework.inmocasa.model.Visita;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.model.enums.Estado;
import org.springframework.inmocasa.model.enums.Genero;
import org.springframework.inmocasa.service.ClienteService;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.inmocasa.service.ValoracionService;
import org.springframework.inmocasa.service.VisitaService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = ValoracionController.class, 
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), 
excludeAutoConfiguration = SecurityConfiguration.class)
@RunWith(SpringRunner.class)
public class ValoracionControllerTests {
	private static final int TEST_VALORACION_ID_1 = 1;
	private static final int TEST_VIVIENDA_ID_1 = 1;
	private static final int TEST_VIVIENDA_ID_2 = 2;
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UsuarioController usuarioController;

	@MockBean
	private VisitaService visitaService;

	@MockBean
	private ValoracionService valoracionService;;
	
	@MockBean
	private	ClienteService clienteService;

	@MockBean
	PropietarioService propietarioService;

	private Valoracion valoracion;
	
	private Visita visita;
	
	private Visita visita2;
	
	private Vivienda vivienda;
	
	private Vivienda vivienda2;
	
	private Cliente cliente;
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
		
		cliente = new Cliente();
		cliente.setId(8);
		cliente.setNombre("John");
		cliente.setApellidos("Doe");
		cliente.setDni("46900025N");
		cliente.setGenero(Genero.MASCULINO);
		cliente.setFechaNacimiento(LocalDate.of(1976, 6, 12));
		cliente.setUsername("john123");
		cliente.setPassword("john123");
		
		visita = new Visita();
		visita.setId(1);
		visita.setFecha(LocalDate.of(2020, 01, 20));
		visita.setLugar("Sevilla");
		visita.setCliente(cliente);
		visita.setVivienda(vivienda);
		
		visita2 = new Visita();
		visita2.setId(2);
		visita2.setFecha(LocalDate.of(2020, 01, 20));
		visita2.setLugar("Malaga");
		visita2.setCliente(cliente);
		visita2.setVivienda(vivienda2);
		
		valoracion = new Valoracion();
		valoracion.setId(TEST_VALORACION_ID_1);
		valoracion.setVisita(visita);
		valoracion.setPuntuacion(4);
		valoracion.setComentario("Muy buena");
		
		prop = new Propietario();
		prop.setId(20);
		prop.setUsername("john1234");
		
	}
	

	@WithMockUser(value = "john123", authorities = { "cliente" })
	@Test
	@DisplayName("Prueba en la que no se crea una valoración porque ya existe")
	void testCreateValoracionNotOk() throws Exception {
		given(this.visitaService.findById2(1)).willReturn(visita);
		given(this.clienteService.findClienteByUsername("john123")).willReturn(Lists.newArrayList(cliente));
		given(this.valoracionService.findByVisita(visita)).willReturn(Lists.newArrayList(valoracion));
		mockMvc.perform(get("/valoracion/{visitaId}/new",1)).andExpect(status().isOk()).andExpect(model().attribute("error","Ya ha realizado una valoración a esta vivienda."))
				.andExpect(view().name("users/visitas"));
	}
	
	@WithMockUser(value = "john123", authorities = { "cliente" })
	@Test
	@DisplayName("Prueba en la que se accede al formulario de valoracion")
	void testCreateFormValoracionOk() throws Exception {
		given(this.visitaService.findById2(2)).willReturn(visita);
		given(this.clienteService.findClienteByUsername("john123")).willReturn(Lists.newArrayList(cliente));
		mockMvc.perform(get("/valoracion/{visitaId}/new",2)).andExpect(status().isOk()).andExpect(model().attributeExists("valoracion"))
				.andExpect(view().name("/visita/valoracion/createValoracionForm"));
	}
	
	@WithMockUser(value = "john123", authorities = { "cliente" })
	@Test
	@DisplayName("Prueba en la que se crea una valoración")
	void testCreateValoracionOk() throws Exception {
		given(this.valoracionService.save(valoracion)).willReturn(valoracion);
		mockMvc.perform(get("/valoracion/save"))
			.andExpect(status().isOk());
	}
	
	@WithMockUser(value="john1234", authorities = { "propietario" })
	@Test
	@DisplayName("Lista de valoraciones a mi vivienda")
	void testListaValoraciones() throws Exception{
		given(this.propietarioService.findPropietarioByUsername("john1234")).willReturn(Lists.list(prop));
		given(this.valoracionService.findAllByPropietario(prop)).willReturn(Lists.list(valoracion));
		
		mockMvc.perform(get("/valoracion/misValoraciones"))
			.andExpect(status().is2xxSuccessful())
			.andExpect(model().attributeExists("valoraciones"));
	}
}
