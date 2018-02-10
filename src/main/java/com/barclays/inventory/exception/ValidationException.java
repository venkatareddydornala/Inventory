package com.barclays.inventory.exception;

/**
 * 
 * Validation Exception
 *
 */
@SuppressWarnings("serial")
public class ValidationException extends Exception {
	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(String message, Exception e) {
		super(message, e);
	}
}