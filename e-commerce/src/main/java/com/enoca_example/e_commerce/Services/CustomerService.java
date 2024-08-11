package com.enoca_example.e_commerce.Services;

import com.enoca_example.e_commerce.DTO.CustomerDTO;
import com.enoca_example.e_commerce.Entity.Cart;
import com.enoca_example.e_commerce.Entity.Customer;
import com.enoca_example.e_commerce.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;


    public CustomerService(CustomerRepository customerRepository) {
        super();
        this.customerRepository = customerRepository;
    }

    public Customer getCustomer(Long customerId){
        return customerRepository.findById(customerId).orElse(null);
    }

    public Customer addCustomer(CustomerDTO customer) {
        Customer newCustomer = new Customer();
        newCustomer.setName(customer.getName());
        newCustomer.setEmail(customer.getEmail());

        Cart cart = new Cart();
        cart.setCustomer(newCustomer);
        cart.setItems(null);
        cart.setTotalPrice(0);
        newCustomer.setCart(cart);

        return customerRepository.save(newCustomer);
    }
}
