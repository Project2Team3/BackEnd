package com.revature.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthenticationException extends RuntimeException{

	public AuthenticationException() {
		super("Invalid username/password");
		Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.error("Invalid username/password");
	}
}
