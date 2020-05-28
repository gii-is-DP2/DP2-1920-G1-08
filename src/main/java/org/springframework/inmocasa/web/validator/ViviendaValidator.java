package org.springframework.inmocasa.web.validator;

import org.springframework.inmocasa.model.Vivienda;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ViviendaValidator implements Validator{

	final private String FECHA_POSTERIOR = "La fecha debe ser posterior al día de hoy";
	final private String VALOR_NEGATIVO = "El valor no puede ser menor que 0";
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Vivienda.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Vivienda vivienda = (Vivienda) obj;
		
		//Validamos dimension y precio
		if(vivienda.getDimensiones()< 0) {
			errors.rejectValue("dimension", VALOR_NEGATIVO, VALOR_NEGATIVO);
		}
		if(vivienda.getPrecio()< 0) {
			errors.rejectValue("precio", VALOR_NEGATIVO, VALOR_NEGATIVO);
		}
		/*
		 * //Validamos la fecha de la visita
		 * if(visita.getFecha().isBefore(LocalDateTime.now())) {
		 * errors.rejectValue("fecha", FECHA_POSTERIOR, FECHA_POSTERIOR); }
		 */
		
	}
}
