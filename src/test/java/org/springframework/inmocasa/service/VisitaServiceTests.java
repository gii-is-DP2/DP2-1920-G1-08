package org.springframework.inmocasa.service;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.inmocasa.model.Cliente;
import org.springframework.inmocasa.model.Visita;
import org.springframework.stereotype.Service;

import com.paypal.base.rest.APIContext;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class VisitaServiceTests {
	
	@MockBean
	APIContext apiContext;
/**
 * public Collection<Visita> findAllByCliente(Cliente cliente) {
		return vr.findAllByCliente(cliente);
	}

	public Collection<Visita> findProximasVisitas(Cliente cliente, LocalDateTime localDateTime) {
		return vr.findProximasVisitas(cliente, localDateTime);
	}
	
	public Collection<Visita> findOldVisitas(Cliente cliente, LocalDateTime localDateTime) {
		return vr.findOldVisitas(cliente, localDateTime);
	}
 */
	
	
	
	
}
