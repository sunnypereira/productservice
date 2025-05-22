package org.lucas.sunny.shopzone.productservice.controller;

import org.lucas.sunny.shopzone.productservice.exception.ErrorResponseDto;
import org.lucas.sunny.shopzone.productservice.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ProductNotFoundException ex) {
	    ErrorResponseDto errorResponseDto = new ErrorResponseDto();
	    errorResponseDto.setStatusCode(HttpStatus.NOT_FOUND.value());
	    errorResponseDto.setMessage(ex.getMessage());
	    errorResponseDto.setTimestamp(System.currentTimeMillis());
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDto);
	}

}
