package com.kronsoft.project.validators;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = {PznValidator.class})
public @interface PznConstraint {
	String message() default "The pzn must contain only numbers!";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
}
