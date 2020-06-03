package org.springframework.inmocasa.web.E2E;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class DenunciaControllerE2ETests {

	@Autowired
	private MockMvc mockMvc;
	
	@WithMockUser(value = "alejandra", authorities= {"cliente"})
	@Test
	@DisplayName("Creación del formulario de la denuncia")
	void createDenunciaForm() throws Exception {
		mockMvc.perform(get("/denuncias/create/{viviendaId}", 2))
			.andExpect(status().is2xxSuccessful())
			.andExpect(view().name("denuncias/nuevaDenuncia"));
	}
	
	@WithMockUser(value = "alejandra", authorities= {"cliente"})
	@Test
	@DisplayName("Generar una denuncia")
	void createDenuncia() throws Exception {
		mockMvc.perform(post("/denuncias/create/{viviendaId}", 1)
				.param("id", "1")
				.param("justificacion", "ES una falsa").with(csrf()))
		.andExpect(view().name("redirect:/viviendas/allNew"));
	}
	
	@WithMockUser(value = "alejandra", authorities= {"cliente"})
	@Test
	@DisplayName("Generar una denuncia error")
	void createDenunciaError() throws Exception {
		mockMvc.perform(post("/denuncias/create/{viviendaId}", 1)
				.param("id", "1").with(csrf()))
		.andExpect(view().name("denuncias/nuevaDenuncia"));
	}
}
