package org.springframework.inmocasa.web.validator;

import org.springframework.inmocasa.model.Valoracion;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ValoracionValidator implements Validator{
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Valoracion.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Valoracion valoracion = (Valoracion) obj;
		
		//Validamos la fecha de la Valoracion
//		if(valoracion.getFecha().isBefore(LocalDateTime.now())) {
//			errors.rejectValue("fecha", FECHA_POSTERIOR, FECHA_POSTERIOR);
//		}
		
		
	}

}
