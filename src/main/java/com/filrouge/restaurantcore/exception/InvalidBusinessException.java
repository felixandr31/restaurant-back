package com.filrouge.restaurantcore.exception;

import lombok.Getter;

/**
 * Exception during invalid business processing.
 * @author Hermann
 *
 */
public class InvalidBusinessException extends FunctionnalException {


	private static final long serialVersionUID = -7865169690560018320L;
	
	@Getter
	private ErrorCodes errorCode;

	public InvalidBusinessException(String message) {
		super(message);
	}

	public InvalidBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidBusinessException(String message, Throwable cause, ErrorCodes errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public InvalidBusinessException(String message, ErrorCodes errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

}
