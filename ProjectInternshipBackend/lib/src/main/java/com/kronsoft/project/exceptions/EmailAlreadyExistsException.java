package com.kronsoft.project.exceptions;

public class EmailAlreadyExistsException extends Exception{

	private static final long serialVersionUID = -5971608172657911911L;
	
	public EmailAlreadyExistsException(String email) {
		super("User with email: " + email + " already exists");
	}
}
