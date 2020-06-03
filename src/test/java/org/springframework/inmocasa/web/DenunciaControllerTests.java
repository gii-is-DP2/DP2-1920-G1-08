package org.springframework.inmocasa.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import java.util.Optional;

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
import org.springframework.inmocasa.model.Vivienda;
import org.springframework.inmocasa.service.DenunciaService;
import org.springframework.inmocasa.service.ViviendaService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = DenunciaController.class, 
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
@RunWith(SpringRunner.class)
public class DenunciaControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ViviendaService viviendaService;
	
	@MockBean
	private DenunciaService denunciaService;
	
	Vivienda vivienda;
	
	@BeforeEach
	void setup() {
		vivienda = new Vivienda();
		vivienda.setId(1);
		
		given(this.viviendaService.findViviendaById(1)).willReturn(Optional.of(vivienda));
	}

	@WithMockUser(value = "manolo")
	@Test
	@DisplayName("Creación del formulario de la denuncia")
	void createDenunciaForm() throws Exception {
		mockMvc.perform(get("/denuncias/create/{viviendaId}", 1))
			.andExpect(status().is2xxSuccessful())
			.andExpect(view().name("denuncias/nuevaDenuncia"));
	}
	
	@WithMockUser(value = "manolo")
	@Test
	@DisplayName("Generar una denuncia")
	void createDenuncia() throws Exception {
		mockMvc.perform(post("/denuncias/create/{viviendaId}", 1)
				.param("id", "1")
				.param("justificacion", "ES una falsa").with(csrf()))
		.andExpect(view().name("redirect:/viviendas/allNew"));
	}
	
	@WithMockUser(value = "manolo")
	@Test
	@DisplayName("Generar una denuncia error")
	void createDenunciaError() throws Exception {
		mockMvc.perform(post("/denuncias/create/{viviendaId}", 1)
				.param("id", "1").with(csrf()))
		.andExpect(view().name("denuncias/nuevaDenuncia"));
	}
	
}
