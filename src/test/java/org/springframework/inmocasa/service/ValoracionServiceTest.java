package org.springframework.inmocasa.service;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.inmocasa.model.Valoracion;
import org.springframework.inmocasa.model.Visita;
import org.springframework.stereotype.Service;

import com.paypal.base.rest.APIContext;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ValoracionServiceTest {


	@Autowired
	protected ValoracionService valoracionService;

	@MockBean
	APIContext apiContext;
	
	@DisplayName("Prueba parametrizada de creación de valoraciones")
	@ParameterizedTest
	@CsvSource({"0, 'lo que sea'","3, 'puntuación intermedia'", "5, 'puntuacion maxima"})
	void testSaveValoracionParametrized(int puntuacion, String comentario) {
		Valoracion v = new Valoracion();
		v.setPuntuacion(puntuacion);
		v.setComentario(comentario);
		Visita vis = new Visita();
		vis.setId(1);
		v.setVisita(vis);
		
		this.valoracionService.save(v);
		Collection<Valoracion> valoraciones = this.valoracionService.findByVisita(vis);

		assertTrue(valoraciones.contains(v));
	}
}
