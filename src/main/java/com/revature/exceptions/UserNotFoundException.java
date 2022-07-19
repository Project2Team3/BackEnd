package com.revature.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserNotFoundException extends RuntimeException{

	public UserNotFoundException(String message) {
		super(message);
		Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.error(message);
	}

	
	
}
