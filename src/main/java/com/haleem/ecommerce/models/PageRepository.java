package com.haleem.ecommerce.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PageRepository extends JpaRepository<Page, Integer> {

	Page findBySlug(String slug);

	/*
	 * @Query("SELECT p FROM Page p where p.id != :id and p.slug = :slug") Page
	 * findBySlug(int id, String slug);
	 */

	Page findBySlugAndId(String slug, int id);

	List<Page> findAllByOrderBySortingAsc();
}
