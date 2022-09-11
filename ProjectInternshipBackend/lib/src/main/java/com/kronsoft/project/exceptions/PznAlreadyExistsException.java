package com.kronsoft.project.exceptions;

public class PznAlreadyExistsException extends Exception{

	private static final long serialVersionUID = 3505772040769321308L;
	
	public PznAlreadyExistsException(String pzn) {
		super("Pzn " + pzn + " already exists!");
	}

}
