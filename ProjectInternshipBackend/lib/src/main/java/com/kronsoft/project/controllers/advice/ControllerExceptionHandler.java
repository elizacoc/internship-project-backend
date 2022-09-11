package com.kronsoft.project.controllers.advice;

import java.util.FormatFlagsConversionMismatchException;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kronsoft.project.exceptions.PznAlreadyExistsException;
import com.kronsoft.project.exceptions.UsernameAlreadyExistsException;
import com.kronsoft.project.exceptions.EmailAlreadyExistsException;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<String> dataIntegrityException(EmailAlreadyExistsException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PznAlreadyExistsException.class)
	public ResponseEntity<String> dataIntegrityException(PznAlreadyExistsException e){
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UsernameAlreadyExistsException.class)
	public ResponseEntity<String> dataIntegrityException(UsernameAlreadyExistsException e){
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> constraintViolationException(ConstraintViolationException e) {
		StringBuilder sb = new StringBuilder();
		e.getConstraintViolations().stream().forEach(exception -> {
			sb.append(exception.getMessage() + "\n");
		});
		return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> methodArgumentNotValidException(MethodArgumentNotValidException e) {
		StringBuilder sb = new StringBuilder();
		e.getAllErrors().stream().forEach(exception -> {
			sb.append(exception.getDefaultMessage() + "\n");
		});
		return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(FormatFlagsConversionMismatchException.class)
	public ResponseEntity<String> formatFlagsConversionMismatchException(FormatFlagsConversionMismatchException e){
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
}
