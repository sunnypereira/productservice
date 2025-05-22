package org.lucas.sunny.shopzone.productservice.exception;

import lombok.Data;

@Data
public class ErrorResponseDto {

	private int statusCode;
	private String message;
	private long timestamp;

}