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
@Constraint(validatedBy = {PasswordSizeValidator.class})
public @interface PasswordSizeConstraint {
	String message() default "Password must be between 8 or 20 characters!";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
}
