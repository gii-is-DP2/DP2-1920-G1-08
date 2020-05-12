package org.springframework.inmocasa.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.inmocasa.InmocasaApplication;
import org.springframework.inmocasa.configuration.SecurityConfiguration;
import org.springframework.inmocasa.service.AdministradorService;
import org.springframework.inmocasa.service.CompraService;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.inmocasa.web.DashboardController;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = DashboardController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { InmocasaApplication.class })
public class DashboardControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CompraService compraService;
	
	@MockBean
	private PropietarioService propietarioService;
	
	@MockBean
	private AdministradorService adminService;
	
	//HU-019: El administrador accede al dashboard correctamente
	@WithMockUser(value = "admin", authorities = { "admin" })
	@Test
	void testAccessDashboardOk() throws Exception {
		mockMvc.perform(get("/dashboard")).andExpect(status().isOk()).andExpect(view().name("dashboard/show"));
	}
	
	
	//HU-019: Alguien no loggeado intenta acceder al dashboard
	@Test
	void testAccessDashboardNotOk() throws Exception {
		mockMvc.perform(get("/dashboard")).andExpect(status().is4xxClientError());
	}

}
