package com.enoca_example.e_commerce.Repository;

import com.enoca_example.e_commerce.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}