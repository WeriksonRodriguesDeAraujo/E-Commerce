package com.serratec.trabalhofinal.model.error;

public class ErrorMessage {
	
	private String title;
	private Integer status;
	private String message;
	private String developerMessage;
	private Long timestamp;
	

	public ErrorMessage(String title, Integer status, String message, String developerMessage, Long timestamp) {
		this.title = title;
		this.status = status;
		this.message = message;
		this.developerMessage = developerMessage;
		this.timestamp = timestamp;
	}
	
	
	public String getTitle() {
		return title;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getDeveloperMessage() {
		return developerMessage;
	}
	
	public Long getTimestamp() {
		return timestamp;
	}
	
}
