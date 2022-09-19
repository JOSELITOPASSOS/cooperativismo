package com.act.cooperativism.exception;

public class UnprocessableEntityException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnprocessableEntityException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnprocessableEntityException(String message) {
		super(message);
	}

}
