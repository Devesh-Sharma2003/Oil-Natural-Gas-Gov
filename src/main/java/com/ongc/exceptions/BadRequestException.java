package com.ongc.exceptions;

import javax.persistence.PersistenceException;

public class BadRequestException extends PersistenceException{
	
	public BadRequestException() {
		super("Bad Request");
	}
	
	public BadRequestException(String msg) {
		super(msg);
	}

}
