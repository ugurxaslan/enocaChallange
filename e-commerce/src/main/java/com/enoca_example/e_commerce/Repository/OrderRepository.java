package com.enoca_example.e_commerce.Repository;

import com.enoca_example.e_commerce.Entity.Orderr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orderr, Long> {
    List<Orderr> findByCustomerId(Long customerId);
    Orderr findByOrderCode(String orderCode);
}
