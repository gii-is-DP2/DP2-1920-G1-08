package org.springframework.inmocasa.web;

import org.springframework.inmocasa.model.Compra;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CompraValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Compra.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Compra compra = (Compra) obj;
		
		//validacion precio
		if (compra.getPrecioFinal() == null) {
			errors.rejectValue("precioFinal", "Hay que especificar un precio", "Hay que especificar un precio");
		}
		
	}

}
