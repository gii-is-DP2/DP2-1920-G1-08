package org.springframework.inmocasa.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.inmocasa.InmocasaApplication;
import org.springframework.inmocasa.configuration.SecurityConfiguration;
import org.springframework.inmocasa.model.Compra;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.service.AdministradorService;
import org.springframework.inmocasa.service.ClienteService;
import org.springframework.inmocasa.service.CompraService;
import org.springframework.inmocasa.service.PaypalService;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.inmocasa.service.ViviendaService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = ViviendaController.class, 
			excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), 
			excludeAutoConfiguration = SecurityConfiguration.class)
//@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { InmocasaApplication.class })
public class PaymentControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PaypalService paypalService;
	
	@MockBean
	private ViviendaService viviendaService;

	@MockBean
	private PropietarioService propietarioService;

	@MockBean
	private CompraService compraService;

	@MockBean
	private AdministradorService adminService;
	
	@MockBean
	private ClienteService clienteService;
	
	
	//HU-021: Publicitar una vivienda
	@WithMockUser(value = "gilmar", authorities = { "propietario" })
	@Test
	void testPublicitarViviendaOK() throws Exception {
		mockMvc.perform(get("/pay/{viviendaId}", 1)).andExpect(status().is3xxRedirection());
	}
	
	//HU-021: Publicitar una vivienda (No permite publicitar ya que no es propietario de la vivienda)
	@Test
	void testPublicitarViviendaNotOK() throws Exception {
		mockMvc.perform(get("/pay/{viviendaId}", 1)).andExpect(status().is4xxClientError());
	}

}
