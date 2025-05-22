package org.lucas.sunny.shopzone.productservice.controller;

import org.lucas.sunny.shopzone.productservice.dto.PagedContent;
import org.lucas.sunny.shopzone.productservice.dto.ProductDto;
import org.lucas.sunny.shopzone.productservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/product")
@CrossOrigin(origins = "*")
public class ProductController {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping()
	public String getMessage() {
		log.info("Hello World!");
		return "Hello World!";
	}

	@GetMapping("title/{title}")
	public ProductDto getProductFromTitle(@PathVariable String title) {
		return productService.getProductByTitle(title);
	}

	@GetMapping("/search")
	public PagedContent<ProductDto> getProductSearchedWithTitle(@RequestParam String title,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size) {
		return productService.getProductSearchedWithTitle(title, page, size);
	}

}
