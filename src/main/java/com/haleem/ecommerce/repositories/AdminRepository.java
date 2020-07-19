package com.haleem.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haleem.ecommerce.models.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

	Admin findByUsername(String username);
}
