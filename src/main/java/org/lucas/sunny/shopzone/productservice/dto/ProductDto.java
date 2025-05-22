package org.lucas.sunny.shopzone.productservice.dto;

import org.lucas.sunny.shopzone.productservice.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
	private Long id;
	private String name;
	private String title;
	private String description;
	private String image;
	private int quantity;
	private double price;
	private String category;
	
	public static ProductDto getProductDtoFromProduct(Product product) {
		ProductDto productDto = new ProductDto();
		productDto.setId(product.getId());
		productDto.setName(product.getName());
		productDto.setTitle(product.getTitle());
		productDto.setDescription(product.getDescription());
		productDto.setImage(product.getImage());
		productDto.setQuantity(product.getQuantity());
		productDto.setPrice(product.getPrice());
		productDto.setCategory(product.getCategory());
		return productDto;
	}
	
}
