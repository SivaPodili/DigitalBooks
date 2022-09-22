package com.DigitalBooks.payload.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserResponse extends ResponseEntity<Object> {

	public UserResponse(HttpStatus status) {
		super(status);
		
	}

}
