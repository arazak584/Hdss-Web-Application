package org.arn.hdsscapture.exception;

public class DataErrorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DataErrorException(String message) {
        super(message);
    }

    public DataErrorException(String message, Throwable cause) {
        super(message, cause);
    }

}
