package org.springframework.inmocasa.web.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.inmocasa.InmocasaApplication;
import org.springframework.inmocasa.model.Compra;
import org.springframework.inmocasa.model.enums.Estado;
import org.springframework.inmocasa.service.CompraService;
import org.springframework.inmocasa.web.CompraController;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompraControllerIntegrationTests {

	@Autowired
	private CompraController compraController;

	@Autowired
	private CompraService compraService;

	@WithMockUser(value = "alonso7", authorities = { "cliente" })
	@Test
	void testAceptarCompra() throws Exception {
		ModelMap model = new ModelMap();
		Compra c = compraService.findCompraByViviendaId(1);
		c.setEstado(Estado.ACEPTADO);
		String view = compraController.aceptarCompra(c.getVivienda().getId(), model);
		assertEquals(view, "redirect:/viviendas/ofertadas");
	}
	
	@WithMockUser(value = "alejandra", authorities = { "cliente" })
    @Test
    void testUpdate() throws Exception {
        ModelMap model = new ModelMap();

        Compra c = compraService.findCompraByViviendaId(3);
        c.setEstado(Estado.RECHAZADO);

        String view = compraController.rechazarCompra(c.getVivienda().getId(), model);

        assertEquals(view, "redirect:/viviendas/ofertadas");
    }
	
	@WithMockUser(value = "alonso7", authorities = { "cliente" })
	@Test
	void testShowCompraDetails() throws Exception {
		ModelMap model = new ModelMap();
		Compra c = compraService.findCompraByViviendaId(1);
		String view = compraController.showCompra(c.getVivienda().getId(), model);
		assertEquals(view, "compras/showCompraDetails");
	}
	
	
	
}