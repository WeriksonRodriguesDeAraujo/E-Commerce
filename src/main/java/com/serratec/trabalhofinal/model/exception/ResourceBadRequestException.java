package com.serratec.trabalhofinal.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceBadRequestException extends RuntimeException {

	private static final long serialVersionUID = -4751974939778489633L;

	public ResourceBadRequestException(String message) {
		super(message);
	}
}
