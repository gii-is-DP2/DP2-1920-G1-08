package org.springframework.inmocasa.web.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.inmocasa.web.UsuarioController;
import org.springframework.inmocasa.web.ViviendaController;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioControllerIntegrationTests {
	
	@Autowired
	UsuarioController usuarioController;

		@DisplayName("Prueba borrar usuario")
		@WithMockUser(value = "gilmar", authorities = { "propietario" })
		@Test
		void testDeleteUsuario() throws Exception {
			ModelMap model = new ModelMap();
			String view = usuarioController.borrarUsuarioCompleto(1, model);
			assertEquals("redirect:/logout",view);
		}
}
