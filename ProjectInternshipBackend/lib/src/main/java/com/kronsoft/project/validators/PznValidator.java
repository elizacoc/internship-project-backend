package com.kronsoft.project.validators;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PznValidator implements ConstraintValidator<PznConstraint, String>{

	private static final String PZN_VALID_FORMAT = "[0-9]+";
	
	@Override
	public boolean isValid(String pzn, ConstraintValidatorContext context) {
		if(Objects.isNull(pzn)) {
			return false;
		}
		if(!pzn.matches(PZN_VALID_FORMAT)) {
			return false;
		}
		return true;
	}

}
