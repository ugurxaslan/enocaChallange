package com.enoca_example.e_commerce.Repository;
import com.enoca_example.e_commerce.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
