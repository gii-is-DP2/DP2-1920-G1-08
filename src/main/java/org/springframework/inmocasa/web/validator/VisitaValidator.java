package org.springframework.inmocasa.web.validator;

import java.time.LocalDate;

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
		if(visita.getFecha()==null) {
			errors.rejectValue("fecha", "Es obligatorio", "Es obligatorio");
		}else if(!visita.getFecha().isAfter(LocalDate.now())) {
			errors.rejectValue("fecha", FECHA_POSTERIOR, FECHA_POSTERIOR);
		}
		
		if(visita.getLugar() == null || visita.getLugar().isEmpty()) {
			errors.rejectValue("lugar", "Es obligatorio", "Es obligatorio");
		}
		
		
	}

}
