package com.enoca_example.e_commerce.Repository;

import com.enoca_example.e_commerce.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
