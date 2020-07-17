package com.haleem.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haleem.ecommerce.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	Category findBySlug(String slug);

	List<Category> findAllByOrderBySortingAsc();

}
