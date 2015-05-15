package com.tooltwist.tddproject.exception;

public class TDDProjectException extends RuntimeException {

	private static final long serialVersionUID = 8271320967403814083L;

	public TDDProjectException() {
		super();
	}

	public TDDProjectException(String message) {
		super(message);
	}

	public TDDProjectException(Throwable cause) {
		super(cause);
	}

	public TDDProjectException(String message, Throwable cause) {
		super(message, cause);
	}

	public TDDProjectException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
