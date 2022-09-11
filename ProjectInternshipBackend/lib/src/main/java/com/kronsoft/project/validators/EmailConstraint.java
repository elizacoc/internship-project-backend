package com.kronsoft.project.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = {EmailValidator.class})
public @interface EmailConstraint {
	String message() default "The email must be a valid format: example@example.com";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
}
