package com.haleem.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haleem.ecommerce.models.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

}
