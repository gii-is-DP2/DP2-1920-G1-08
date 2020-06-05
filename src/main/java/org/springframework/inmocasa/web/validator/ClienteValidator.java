package org.springframework.inmocasa.web.validator;

import java.time.LocalDate;
import java.util.regex.Pattern;

import org.springframework.inmocasa.model.Cliente;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ClienteValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Cliente.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Cliente p = (Cliente) target;
		
		if(p.getDni() == null) {
			errors.rejectValue("dni", "DNI es obligatorio", "DNI es obligatorio");
		}else if(!p.getDni().matches("[0-9]{8}[A-Z]")) {
			errors.rejectValue("dni", "DNI incorrecto", "DNI incorrecto");
		}
		if(!(p.getTelefono()== null || p.getTelefono().isEmpty()) && !p.getTelefono().matches("[0-9]{9}")) {
			errors.rejectValue("telefono", "Teléfono incorrecto, debe ser 9 dígitos", "Teléfono incorrecto, debe ser 9 dígitos");
		}
		if(p.getFechaNacimiento()!= null && !p.getFechaNacimiento().isBefore(LocalDate.now())) {
			errors.rejectValue("fechaNacimiento","La fecha de nacimiento tiene que ser anterior al día de hoy","La fecha de nacimiento tiene que ser anterior al día de hoy");
		}
		Pattern email_validator = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		if(!(p.getEmail()== null || p.getEmail().isEmpty()) && !email_validator.matcher(p.getEmail()).find()) {
			errors.rejectValue("email", "El email no tiene el formato correcto", "El email no tiene el formato correcto");
		}
		
	}

}
