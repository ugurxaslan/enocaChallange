package com.enoca_example.e_commerce.Controller;

import com.enoca_example.e_commerce.DTO.CustomerDTO;
import com.enoca_example.e_commerce.Entity.Customer;
import com.enoca_example.e_commerce.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        super();
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody CustomerDTO customer) {

        Customer addedCustomer = customerService.addCustomer(customer);
        return ResponseEntity.ok(addedCustomer);
    }

}
