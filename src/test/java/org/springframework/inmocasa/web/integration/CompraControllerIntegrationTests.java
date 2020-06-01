package org.springframework.inmocasa.web.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.inmocasa.model.Compra;
import org.springframework.inmocasa.model.enums.Estado;
import org.springframework.inmocasa.service.ClienteService;
import org.springframework.inmocasa.service.CompraService;
import org.springframework.inmocasa.service.ViviendaService;
import org.springframework.inmocasa.web.CompraController;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompraControllerIntegrationTests {
	
	private static final int TEST_VIVIENDA_ID = 2;
	
	private static final int TEST_CLIENTE_ID = 8;

	@Autowired
	private CompraController compraController;

	@Autowired
	private CompraService compraService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ViviendaService viviendaService;

	@WithMockUser(value = "alonso7", authorities = { "cliente" })
	@Test
	void testAceptarCompra() throws Exception {
		ModelMap model = new ModelMap();
		Compra c = compraService.findCompraById(1);
		c.setEstado(Estado.ACEPTADO);
		String view = compraController.aceptarCompra(c.getVivienda().getId(), model);
		assertEquals(view, "redirect:/viviendas/ofertadas");
	}
	
	@WithMockUser(value = "alejandra", authorities = { "cliente" })
    @Test
    void testUpdate() throws Exception {
        ModelMap model = new ModelMap();

        Compra c = compraService.findCompraById(2);
        c.setEstado(Estado.RECHAZADO);

        String view = compraController.rechazarCompra(c.getVivienda().getId(), model);
        assertEquals(view, "redirect:/viviendas/ofertadas");
    }
	
	@WithMockUser(value = "alonso7", authorities = { "cliente" })
	@Test
	void testShowCompraDetails() throws Exception {
		ModelMap model = new ModelMap();
		Compra c = compraService.findCompraById(1);
		String view = compraController.showCompra(c.getVivienda().getId(), model);
		assertEquals(view, "compras/showCompraDetails");
	}
	
	@WithMockUser(value = "rodrigo", authorities = { "cliente" })
	@Test
	void testRealizarOferta() throws Exception {
		ModelMap model = new ModelMap();
		String view = compraController.create(TEST_VIVIENDA_ID, model);
		assertEquals(view, "compras/form");
	}
	
	
	@WithMockUser(value = "rodrigo", authorities = { "cliente" })
	@Test
	void testComprar() throws Exception {
		ModelMap model = new ModelMap();
		Compra c = new Compra();
		c.setCliente(clienteService.findClienteById(TEST_CLIENTE_ID));
		c.setEstado(Estado.PENDIENTE);
		c.setPrecioFinal(100000);
		c.setVivienda(viviendaService.findViviendaId(TEST_VIVIENDA_ID));
		BindingResult bindingResult = new MapBindingResult(Collections.emptyMap(), "");
		String view = compraController.save(TEST_VIVIENDA_ID, c, bindingResult , model);
		assertEquals(view, "redirect:/viviendas/allNew");
	}
	
	
	
}