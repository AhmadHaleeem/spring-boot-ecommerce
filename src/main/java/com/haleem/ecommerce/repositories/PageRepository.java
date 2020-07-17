package com.haleem.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haleem.ecommerce.models.Page;

public interface PageRepository extends JpaRepository<Page, Integer> {

	Page findBySlug(String slug);

	/*
	 * @Query("SELECT p FROM Page p where p.id != :id and p.slug = :slug") Page
	 * findBySlug(int id, String slug);
	 */

	List<Page> findAllByOrderBySortingAsc();

	Page findBySlugAndIdNot(String slug, int id);
}
