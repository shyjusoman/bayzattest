package com.mycom.platform.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mycom.platform.exceptions.EntityNotFoundException;
import com.mycom.platform.exceptions.response.ApiErrorDetails;

@ControllerAdvice
@RestController
public class ImplResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	  @ExceptionHandler(EntityNotFoundException.class)
	  public final ResponseEntity<ApiErrorDetails> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
		  ApiErrorDetails errorDetails = new ApiErrorDetails(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	  }
	  
	  @ExceptionHandler(Exception.class)
	  public final ResponseEntity<ApiErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
		  ApiErrorDetails errorDetails = new ApiErrorDetails(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
}
