package org.lucas.sunny.shopzone.productservice.controller;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lucas.sunny.shopzone.productservice.BaseTestConfigurations;
import org.lucas.sunny.shopzone.productservice.dto.PagedContent;
import org.lucas.sunny.shopzone.productservice.dto.ProductDto;
import org.lucas.sunny.shopzone.productservice.entity.Product;
import org.lucas.sunny.shopzone.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ProductControllerTests extends BaseTestConfigurations {

	@Autowired
	ProductRepository productRepository;

	@BeforeEach
	void setUp() {
		RestAssured.port = port;
	}

	@Test
	void faliTest() {
		assertFalse(true);
	}

	@Test
	void shouldFindProductWithTitle() throws Exception {
		// get a product
		Product p = createProduct();
		// save product to database
		p = productRepository.save(p);

		int id = p.getId().intValue(); // needed for int type matching
		float price = (float) (p.getPrice()); // all decimal numbers are matched as floats

		when().get("/api/product/title/{title}", "product-title").then().statusCode(200).body("id", equalTo(id))
				.body("name", equalTo(p.getName())).body("title", equalTo(p.getTitle()))
				.body("description", equalTo(p.getDescription())).body("image", equalTo(p.getImage()))
				.body("quantity", equalTo(p.getQuantity())).body("price", equalTo(price))
				.body("category", equalTo(p.getCategory()));

		productRepository.deleteAll();
	}

	@Test
	void shouldNotFindProductWithTitle() {
		Long beforeRequestTimestamp = System.currentTimeMillis();
		Response response = when().get("/api/product/title/{title}", "product-title").then().statusCode(404)
				.body("statusCode", equalTo(404))
				.body("message", equalTo("Product not found with title: product-title")).extract().response();
		Long afterRequestTimestamp = System.currentTimeMillis();
		Long jsonBodyTimestamp = response.getBody().jsonPath().getLong("timestamp");

		boolean beforeTimeIsLessOrEqual = beforeRequestTimestamp <= jsonBodyTimestamp;
		boolean afterTimeIsMoreOrEqual = afterRequestTimestamp >= jsonBodyTimestamp;

		assertTrue(beforeTimeIsLessOrEqual);
		assertTrue(afterTimeIsMoreOrEqual);

	}

	@SuppressWarnings("unchecked")
	@Test
	void shouldFindMultipleProductsWithTitleSearch() {
		// get a product
		Product p = createProduct();
		// save product to database
		p = productRepository.save(p);
		
		p.setId(p.getId() + 1);
		p.setTitle("product-title2");
		// save the product to database
		productRepository.save(p);

//		Response response= when().get("/api/product/search?title=product&page=0&size=2").then().statusCode(200).extract().response();
//		List<Product> productList = (List<Product>) response.getBody().jsonPath().getJsonObject("data"); // this works

//		PagedContent<ProductDto> pagedContent = (PagedContent<ProductDto>) when()
//				.get("/api/product/search?title=product&page=0&size=2").then().statusCode(200).extract()
//				.as(PagedContent.class);

		PagedContent<ProductDto> pagedContent = (PagedContent<ProductDto>) given().queryParam("title", "product")
				.queryParam("page", "0").queryParam("size", "2").when() // not sure why I am using this, but seems like
																		// a good practise
				.get("/api/product/search").then().statusCode(200).extract().as(PagedContent.class);

		List<ProductDto> productDtoList = pagedContent.getData();

		assertEquals(2, productDtoList.size());
		assertEquals(2, pagedContent.getTotalElements());
		assertEquals(1, pagedContent.getTotalPages());
		assertEquals(0, pagedContent.getPageNumber());
		assertTrue(pagedContent.isFirst());
		assertTrue(pagedContent.isLast());
		assertFalse(pagedContent.isHasNext());
		assertFalse(pagedContent.isHasPrevious());
		
		productRepository.deleteAll();

	}

	@SuppressWarnings("unchecked")
	@Test
	void shouldNotFindMultipleProductsWithTitle() {
		Response response = when().get("/api/product/search?title=product&page=0&size=2").then().extract().response();
		PagedContent<ProductDto> pagedContent = (PagedContent<ProductDto>) response.as(PagedContent.class);
		List<ProductDto> productDtoList = pagedContent.getData();

		assertEquals(200, response.getStatusCode());
		assertEquals(0, productDtoList.size());
		assertEquals(0, pagedContent.getTotalElements());
		assertEquals(0, pagedContent.getTotalPages());
		assertEquals(0, pagedContent.getPageNumber());
		assertTrue(pagedContent.isFirst());
		assertTrue(pagedContent.isLast());
		assertFalse(pagedContent.isHasNext());
		assertFalse(pagedContent.isHasPrevious());
	}

	private Product createProduct() {
		Product product = new Product();
		product.setName("productName");
		product.setTitle("product-title");
		product.setDescription("productDescription");
		product.setImage("product-image");
		product.setQuantity(20);
		product.setPrice(12.99);
		product.setCategory("category");
		return product;
	}
}
