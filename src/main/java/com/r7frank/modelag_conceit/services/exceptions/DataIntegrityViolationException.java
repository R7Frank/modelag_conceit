package com.r7frank.modelag_conceit.services.exceptions;

public class DataIntegrityViolationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public DataIntegrityViolationException(String msg) {
		super(msg);
	}

	public DataIntegrityViolationException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
