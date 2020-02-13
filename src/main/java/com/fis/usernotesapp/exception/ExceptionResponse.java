package com.fis.usernotesapp.exception;

import java.util.Date;

/**
 * Class to create object in case of exception
 * @author Akhil Garg
 *
 */
public class ExceptionResponse {

	
	private Date timestamp;
	private String message;
	
	/**
	 * Paramterized constructor
	 * @param timestamp
	 * @param messag
	 */
	public ExceptionResponse(Date timestamp, String messag) {
		super();
		this.timestamp = timestamp;
		this.message = messag;
		
	}

	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
