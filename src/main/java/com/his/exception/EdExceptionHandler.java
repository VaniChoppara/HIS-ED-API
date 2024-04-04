package com.his.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class EdExceptionHandler {
	
	// Ed Custom Exception
	@ExceptionHandler(value=EdException.class)
	public ResponseEntity<EdApiError> handleEdException(EdException e) {
		EdApiError apiError=new EdApiError("ED-API-05", e.getMessage(), new Date());
		return new ResponseEntity<EdApiError>(apiError,HttpStatus.BAD_REQUEST);
	};
	
	@ExceptionHandler(value=Exception.class)
	public ResponseEntity<EdApiError> handleException(Exception e) {
		EdApiError apiError=new EdApiError("ED-API-05", e.getMessage(), new Date());
		return new ResponseEntity<EdApiError>(apiError,HttpStatus.BAD_REQUEST);
	};

}
