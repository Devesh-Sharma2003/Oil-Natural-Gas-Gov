package com.ongc.exceptions;

import javax.persistence.PersistenceException;

public class UnauthorizedException extends PersistenceException{
		
		public UnauthorizedException() {
			super("Unauthorized access");
		}
		
		public UnauthorizedException(String msg) {
			super(msg);
		}

}
