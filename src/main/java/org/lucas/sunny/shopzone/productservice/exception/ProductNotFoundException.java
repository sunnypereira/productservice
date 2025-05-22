package org.lucas.sunny.shopzone.productservice.exception;

public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1986006360001756L;
	
	public ProductNotFoundException(String message) {
		super(message);
	}
	
}
