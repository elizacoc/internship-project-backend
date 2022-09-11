package com.kronsoft.project.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordSizeValidator implements ConstraintValidator<PasswordSizeConstraint, String>{

	@Override
	public boolean isValid(String rawPassword, ConstraintValidatorContext context) {
		if(rawPassword.length() < 8 || rawPassword.length() > 20) {
			return false;
		}
		return true;
	}

}
