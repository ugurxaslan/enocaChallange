package com.enoca_example.e_commerce.Controller;

import com.enoca_example.e_commerce.DTO.CustomerDTO;
import com.enoca_example.e_commerce.Entity.Cart;
import com.enoca_example.e_commerce.Entity.Customer;
import com.enoca_example.e_commerce.Repository.CustomerRepository;
import com.enoca_example.e_commerce.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        super();
        this.customerService = customerService;
    }

    @PostMapping("/addCustomer")
    public ResponseEntity<Customer> addCustomer(@RequestBody CustomerDTO customer) {

        Customer addedCustomer = customerService.addCustomer(customer);
        return ResponseEntity.ok(addedCustomer);
    }

    @GetMapping("/{customerId}/getCustomer")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long customerId) {
        Customer customer = customerService.getCustomer(customerId);
        return ResponseEntity.ok(customer);
    }
}
