package org.springframework.inmocasa.web.validator;

import java.time.LocalDateTime;

import org.springframework.inmocasa.model.Visita;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class VisitaValidator implements Validator{
	
	final private String FECHA_POSTERIOR = "La fecha debe ser posterior al día de hoy";
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Visita.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Visita visita = (Visita) obj;
		
		//Validamos la fecha de la visita
		if(visita.getFecha().isBefore(LocalDateTime.now())) {
			errors.rejectValue("fecha", FECHA_POSTERIOR, FECHA_POSTERIOR);
		}
		
		
	}

}
