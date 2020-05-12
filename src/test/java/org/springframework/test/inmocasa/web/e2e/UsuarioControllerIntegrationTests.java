package org.springframework.test.inmocasa.web.e2e;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.inmocasa.service.AdministradorService;
import org.springframework.inmocasa.service.ClienteService;
import org.springframework.inmocasa.service.CompraService;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.inmocasa.service.UsuarioService;
import org.springframework.inmocasa.service.VisitaService;
import org.springframework.inmocasa.service.ViviendaService;
import org.springframework.inmocasa.web.ViviendaController;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class UsuarioControllerIntegrationTests {

	@Autowired
	private MockMvc mockMvc;

	// HU-023: Borrar un usuario
	@WithMockUser(value = "alonso7", authorities = { "cliente" })
	@Test
	void testDeleteUsuarioOk() throws Exception {
		mockMvc.perform(get("/usuario/delete/{usuarioId}", 10)).andExpect(status().isOk());
	}

	// HU-023: Borrar un usuario (No se borra porque no hay nadie logueado)
	@Test
	void testDeleteUsuarioNotOk() throws Exception {
		mockMvc.perform(get("/usuario/delete/{usuarioId}", 1)).andExpect(status().is4xxClientError());
	}

}
