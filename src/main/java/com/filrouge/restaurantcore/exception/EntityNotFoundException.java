package com.filrouge.restaurantcore.exception;

import lombok.Getter;

/**
 * Exception when an entity is not found in the database
 * 
 * @author Hermann
 *
 */
public class EntityNotFoundException extends FunctionnalException {

	private static final long serialVersionUID = -302637845112243979L;
	
	@Getter
	private ErrorCodes errorCode;

	public EntityNotFoundException(String message) {
		super(message);
	}

	public EntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntityNotFoundException(String message, Throwable cause, ErrorCodes errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public EntityNotFoundException(String message, ErrorCodes errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

}
