package com.arn.hdss.exception;

public class DataNotFoundException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public DataNotFoundException(String record, String id) {
		super("Could not find " + record + " with id: " + id);
	}
	
	public DataNotFoundException(String record, Long id) {
		super("Could not find " + record + " with id: " + id);
	}


}
