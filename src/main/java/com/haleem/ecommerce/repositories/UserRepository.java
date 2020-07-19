package com.haleem.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haleem.ecommerce.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);
}
