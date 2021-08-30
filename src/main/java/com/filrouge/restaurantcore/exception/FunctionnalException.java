package com.filrouge.restaurantcore.exception;

import lombok.Getter;

/**
 * Exception fontionnelles parente.
 * @author pgosse
 *
 */
public class FunctionnalException extends RuntimeException {


	private static final long serialVersionUID = -7865169690560018320L;
	
	@Getter
	private ErrorCodes errorCode;

	public FunctionnalException(String message) {
		super(message);
	}

	public FunctionnalException(String message, Throwable cause) {
		super(message, cause);
	}

	public FunctionnalException(String message, Throwable cause, ErrorCodes errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public FunctionnalException(String message, ErrorCodes errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

}
