package org.springframework.inmocasa.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import org.springframework.inmocasa.model.Usuario;
import org.springframework.inmocasa.service.AdministradorService;
import org.springframework.inmocasa.service.ClienteService;
import org.springframework.inmocasa.service.CompraService;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.inmocasa.service.UsuarioService;
import org.springframework.inmocasa.service.VisitaService;
import org.springframework.inmocasa.service.ViviendaService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = ViviendaController.class, 
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), 
excludeAutoConfiguration = SecurityConfiguration.class)
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { InmocasaApplication.class })
public class UsuarioControllerTests {
	
	private static final int TEST_ID_USUARIO = 10;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private VisitaService visitaService;

	@MockBean
	private ClienteService clienteService;

	@MockBean
	private PropietarioService propietarioService;

	@MockBean
	private UsuarioService usuarioService;

	@MockBean
	private ViviendaService viviendaService;

	@MockBean
	private CompraService compraService;

	@MockBean
	private AdministradorService adminstradorService;

	private Usuario usuario;
	
	@BeforeEach
	void setup() {
		usuario = new Usuario();
		usuario.setId(TEST_ID_USUARIO);
		usuario.setUsername("alonso7");
		usuario.setPassword("alonso7");
		usuario.setNombre("Alonso");
		usuario.setDni("29504326H");
		
		given(this.usuarioService.findUsuarioById(TEST_ID_USUARIO)).willReturn(usuario);
	}

	// HU-023: Borrar un usuario
	@WithMockUser(value = "alonso7", authorities = { "cliente" })
	@Test
	void testDeleteUsuarioOk() throws Exception {
		
		mockMvc.perform(get("/usuario/delete/{usuarioId}", TEST_ID_USUARIO)).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/logout"));
	}

	// HU-023: Borrar un usuario
	@Test
	void testDeleteUsuarioNotOk() throws Exception {
		mockMvc.perform(get("/usuario/delete/{usuarioId}", 1)).andExpect(status().is4xxClientError());
	}

}
