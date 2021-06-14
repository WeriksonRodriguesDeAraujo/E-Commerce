package com.serratec.trabalhofinal.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ResourceForbiddenException extends RuntimeException{
    
    private static final long serialVersionUID = -9151974939778489608L;

	public ResourceForbiddenException(String message) {
		super(message);
	}
}
