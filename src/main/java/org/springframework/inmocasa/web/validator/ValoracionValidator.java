package org.springframework.inmocasa.web.validator;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
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

		if(valoracion.getPuntuacion() == null) {
			errors.rejectValue("puntuacion", "El campo es obligatorio", "El campo es obligatorio");
		}else if(valoracion.getPuntuacion()< 0 || valoracion.getPuntuacion()>5) {
			errors.rejectValue("puntuacion", "La puntiacion tiene que ser entre 0 y 5", "La puntiacion tiene que ser entre 0 y 5");
		}
		if(valoracion.getComentario() == null || valoracion.getComentario().isEmpty()) {
			errors.rejectValue("comentario", "El campo es obligatorio", "El campo es obligatorio");
		}
		
	}

}
