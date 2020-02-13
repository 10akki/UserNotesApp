package com.fis.usernotesapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class to handle usernotes related exception
 * @author Akhil Garg
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotesException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	public UserNotesException() {
        super();
    }

    /**
     * Paramterized constructor taking single arguement
     * @param message
     */
    public UserNotesException(String message) {
        super(message);
    }

    /**
     * Paramterized constructor taking two paramters
     * @param message
     * @param cause
     */
    public UserNotesException(String message, Throwable cause) {
        super(message, cause);
    }
}