package com.valentim.cursomc6.service.exceptions;

public class DataIntgrityException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public DataIntgrityException(String msg) {
		super(msg);
	}
	
	public DataIntgrityException(String msg, Throwable cause) {
		super(msg,cause);
	}

}
