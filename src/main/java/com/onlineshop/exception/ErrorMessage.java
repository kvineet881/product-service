package com.onlineshop.exception;
import java.util.Date;

public class ErrorMessage {
	private Date timestamp;
	private String message;
	private String traceId;
	
	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	public ErrorMessage() {}
	
	

	public ErrorMessage(Date timestamp, String message, String traceId) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.traceId = traceId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
