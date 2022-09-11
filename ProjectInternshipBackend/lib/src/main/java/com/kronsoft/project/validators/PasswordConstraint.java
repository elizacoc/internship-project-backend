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
@Constraint(validatedBy = {PasswordValidator.class})
public @interface PasswordConstraint {
	String message() default "The password must contain at least 1 capital letter, 1 small letter, 1 digit and 1 symbol!";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
}
