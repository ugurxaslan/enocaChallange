package com.enoca_example.e_commerce.Repository;

import com.enoca_example.e_commerce.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
