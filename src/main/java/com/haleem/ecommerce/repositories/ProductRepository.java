package com.haleem.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haleem.ecommerce.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findBySlug(String slug);

}
