package com.myprojects.digitalacademy.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.myprojects.digitalacademy.service.exception.BusinessException;
import com.myprojects.digitalacademy.service.exception.NotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(BusinessException.class)
	private ResponseEntity<String> handleBusinessException(BusinessException ex){
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
	}
	
	@ExceptionHandler(NotFoundException.class)
	private ResponseEntity<String> handleNotFoundException(NotFoundException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	
}
