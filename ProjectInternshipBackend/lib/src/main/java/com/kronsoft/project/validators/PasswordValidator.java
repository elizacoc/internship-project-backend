package com.kronsoft.project.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String>{

	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{1,100})";

	@Override
	public boolean isValid(String rawPassword, ConstraintValidatorContext context) {
		if(!rawPassword.matches(PASSWORD_PATTERN)) {
			return false;
		}
		return true;
	}

}
