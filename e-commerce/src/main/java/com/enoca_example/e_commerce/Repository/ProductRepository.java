package com.enoca_example.e_commerce.Repository;
import com.enoca_example.e_commerce.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
