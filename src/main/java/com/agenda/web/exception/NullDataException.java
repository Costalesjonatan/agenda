package com.agenda.web.exception;

public class NullDataException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public NullDataException(String message) {
		super(message);
	}
	
	public NullDataException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
