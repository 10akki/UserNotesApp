package com.fis.usernotesapp.exception;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Exception handler class
 * @author Akhil Garg
 *
 */
@RestController
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
	
	/**
	 * Method to handle all generic exception
	 * @param ex
	 * @param request 
	 * @return ResponseEntity 
	 * 			describing exception reason
	 * @throws Exception
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllException(Exception ex, WebRequest request) throws Exception {
		ExceptionResponse res=new ExceptionResponse(new Date(), ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(res,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * Method to handle UserNotesException
	 * @param ex
	 * @param request
	 * @return ResponseEntity
	 * 			describing exception reason
	 * @throws Exception
	 */
	@ExceptionHandler(UserNotesException.class)
	public final ResponseEntity<ExceptionResponse> handleResourceException(Exception ex, WebRequest request) throws Exception {
		ExceptionResponse res=new ExceptionResponse(new Date(), ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(res,HttpStatus.NOT_FOUND);
	}
	
	/*
	 *Method to handle/validate inputs provided by the user
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		  ExceptionResponse res=new ExceptionResponse(new Date(), ex.getBindingResult()
		  .getFieldErrors() .stream() .map(x -> x.getDefaultMessage())
		  .collect(Collectors.toList()).toString());
		return new ResponseEntity<Object>(res,HttpStatus.BAD_REQUEST);
	}
}