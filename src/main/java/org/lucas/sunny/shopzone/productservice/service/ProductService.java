package org.lucas.sunny.shopzone.productservice.service;

import java.util.Optional;

import org.lucas.sunny.shopzone.productservice.dto.PagedContent;
import org.lucas.sunny.shopzone.productservice.dto.ProductDto;
import org.lucas.sunny.shopzone.productservice.entity.Product;
import org.lucas.sunny.shopzone.productservice.exception.ProductNotFoundException;
import org.lucas.sunny.shopzone.productservice.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public ProductDto getProductByTitle(String title) {

		Optional<Product> optionalProduct = productRepository.findByTitleIgnoreCase(title);
		if (optionalProduct.isEmpty()) {
			log.error("Product not found with title: {}", title);
			throw new ProductNotFoundException("Product not found with title: " + title);
		}
		Product product = optionalProduct.get();
		ProductDto productDto = ProductDto.getProductDtoFromProduct(product);
		return productDto;
	}

	public PagedContent<ProductDto> getProductSearchedWithTitle(String title, int page, int size) {
		Page<Product> pagedProduct = productRepository.findByTitleContainingIgnoreCase(title,
				PageRequest.of(page, size));

		Page<ProductDto> pagedProductDto = pagedProduct.map(ProductDto::getProductDtoFromProduct);

		PagedContent<ProductDto> pagedContent = new PagedContent<ProductDto>() {
			{
				setData(pagedProductDto.getContent());
				setTotalElements(pagedProductDto.getTotalElements());
				setPageNumber(pagedProductDto.getNumber());
				setTotalPages(pagedProductDto.getTotalPages());
				setFirst(pagedProductDto.isFirst());
				setLast(pagedProductDto.isLast());
				setHasNext(pagedProductDto.hasNext());
				setHasPrevious(pagedProductDto.hasPrevious());
			}
		};

		return pagedContent;

	}

}
