package org.springframework.inmocasa.web.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.inmocasa.InmocasaApplication;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Compra;
import org.springframework.inmocasa.model.Mensaje;
import org.springframework.inmocasa.model.Propietario;
import org.springframework.inmocasa.model.enums.Estado;
import org.springframework.inmocasa.model.enums.Genero;
import org.springframework.inmocasa.service.ClienteService;
import org.springframework.inmocasa.service.CompraService;
import org.springframework.inmocasa.service.MensajeService;
import org.springframework.inmocasa.service.PropietarioService;
import org.springframework.inmocasa.web.CompraController;
import org.springframework.inmocasa.web.MensajeController;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MensajeControllerIntegrationTests {

	@Autowired
	private MensajeController mensajeController;

	@Autowired
	private MensajeService mensajeService;

	@Autowired
	private PropietarioService propietarioService;

	@Autowired
	private ClienteService clienteService;

	private static final int TEST_MENSAJE_ID = 1;
	private static final int TEST_CLIENTE_ID = 8;
	private static final int TEST_PROPIETARIO_ID = 1;

	@WithMockUser(value = "alonso7", authorities = { "cliente" })
	@Test
	void testInitCreate() throws Exception {
		ModelMap model = new ModelMap();
		String view = mensajeController.crearMensaje(model);
		assertEquals(view, "mensajes/editMensaje");
	}

	@WithMockUser(value = "gilmar", authorities = { "propietario" })
	@Test
	void testProcessCreationForm() throws Exception {
		ModelMap model = new ModelMap();
		Mensaje mensaje = new Mensaje();
		mensaje.setId(TEST_MENSAJE_ID);
		mensaje.setAsunto("Hola");
		mensaje.setCuerpo("CASA");
		Propietario prop = propietarioService.findPropietarioById(TEST_PROPIETARIO_ID);
		Cliente cliente = clienteService.findClienteById(TEST_CLIENTE_ID);
		mensaje.setClient(cliente);
		mensaje.setProp(prop);
		mensaje.setEmisorId(prop.getId());
		mensaje.setReceptorId(cliente.getId());
		BindingResult bindingResult = new MapBindingResult(Collections.emptyMap(), "");

		String view = mensajeController.guardarMensaje(mensaje, bindingResult, model);

		assertEquals(view, "mensajes/misMensajes");
	}
	
	
	@WithMockUser(value = "gilmar", authorities = { "propietario" })
	@Test
	void testMensajesEnviados() throws Exception {
		ModelMap model = new ModelMap();
		String view = mensajeController.listadoMensajesEnviados(model);
		assertEquals(view, "mensajes/misMensajes");
	}
	@WithMockUser(value = "gilmar", authorities = { "propietario" })
	@Test
	void testMensajesRecibidos() throws Exception {
		ModelMap model = new ModelMap();
		String view = mensajeController.listadoMensajesRecibidos(model);
		assertEquals(view, "mensajes/misMensajes");
	}

}