package com.serratec.trabalhofinal.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ResourceUnauthorizedException extends RuntimeException {
    
    private static final long serialVersionUID = -6751974939778489653L;

	public ResourceUnauthorizedException(String message) {
		super(message);
	}
}
