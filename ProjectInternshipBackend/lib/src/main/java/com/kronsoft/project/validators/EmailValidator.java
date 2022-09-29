package com.kronsoft.project.validators;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailConstraint, String>{

	private static final String EMAIL_VALID_FORMAT = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";
	
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if(Objects.isNull(email)) {
			return false;
		}
		if(!email.matches(EMAIL_VALID_FORMAT)){
			return false;
		}
		return true;
	}

}
