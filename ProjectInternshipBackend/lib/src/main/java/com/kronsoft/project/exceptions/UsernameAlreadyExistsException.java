package com.kronsoft.project.exceptions;

public class UsernameAlreadyExistsException extends Exception{

	private static final long serialVersionUID = 8171857466879977217L;

	public UsernameAlreadyExistsException(String username) {
		super("Username " + username + " already exists");
	}
}
