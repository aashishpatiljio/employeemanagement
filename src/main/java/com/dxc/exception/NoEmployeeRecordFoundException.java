package com.dxc.exception;

public class NoEmployeeRecordFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoEmployeeRecordFoundException() {

	}

	public NoEmployeeRecordFoundException(String message) {
		super(message);
	}
}
