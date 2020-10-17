package com.springboot.its.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserAlreadyAssignedException extends RuntimeException {
	public UserAlreadyAssignedException(String message) {
		super(message);
	}
}


