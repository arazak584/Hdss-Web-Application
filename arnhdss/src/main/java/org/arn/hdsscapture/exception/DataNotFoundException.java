package org.arn.hdsscapture.exception;

public class DataNotFoundException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public DataNotFoundException(String record, String id) {
		super("Could not find " + record + " with id: " + id);
	}



}
