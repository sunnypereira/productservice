package org.lucas.sunny.shopzone.productservice.repository;

import java.util.Optional;

import org.lucas.sunny.shopzone.productservice.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Optional<Product> findByTitleIgnoreCase(String title);
	Page<Product> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
