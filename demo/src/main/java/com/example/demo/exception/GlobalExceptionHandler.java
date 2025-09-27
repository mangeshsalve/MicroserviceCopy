package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public 	Map<String,String> handleValidException(MethodArgumentNotValidException ex){
		Map<String, String> error=new HashMap<String, String>();
		ex.getBindingResult().getFieldErrors().forEach(
				
				er->error.put(er.getField(), er.getDefaultMessage())
				);
		
		return error;
		
	}
	
	@ExceptionHandler(UserNotExistExceptions.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public Map<String,String> handleUserException(UserNotExistExceptions ex){
		Map<String, String> error=new HashMap<String, String>();
		error.put("Error", ex.getMessage());
		return error;
		
	}

}
