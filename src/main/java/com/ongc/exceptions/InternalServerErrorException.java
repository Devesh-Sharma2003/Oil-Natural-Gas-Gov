package com.ongc.exceptions;

import javax.persistence.PersistenceException;

import org.slf4j.Logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InternalServerErrorException extends PersistenceException{
	
	public InternalServerErrorException() {
		super();
	}
	
	public InternalServerErrorException(String msg) {
		super("unable to fetch data at a moment! please try again later");
//		log.info("INTERNAL SERVER ERROR",msg);
	}

}
