package org.lucas.sunny.shopzone.productservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "product", schema = "product_service")
@Data
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@Column(name="image")
	private String image;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="price")
	private double price;
	
	@Column(name="category")
	private String category;

}


//(
//  id BIGSERIAL PRIMARY KEY,
//  name TEXT DEFAULT NULL,
//  title TEXT DEFAULT NULL,
//  description TEXT DEFAULT NULL,
//  image TEXT DEFAULT NULL,
//  quantity INTEGER DEFAULT NULL,
//  price numeric(8,2) DEFAULT NULL,
//  category VARCHAR(11) DEFAULT NULL
//);





