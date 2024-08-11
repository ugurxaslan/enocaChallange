package com.enoca_example.e_commerce.Controller;
import com.enoca_example.e_commerce.Entity.Orderr;
import com.enoca_example.e_commerce.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{orderCode}/getOrder")
    public ResponseEntity<Orderr> getOrderForCode(@PathVariable String orderCode) {
        Orderr order = orderService.getOrderForCode(orderCode);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{customerId}/getAllOrder")
    public ResponseEntity<List<Orderr>> getAllOrdersForCustomer(@PathVariable Long customerId) {
        List<Orderr> orders = orderService.getAllOrdersForCustomer(customerId);
        if (!orders.isEmpty()) {
            return ResponseEntity.ok(orders);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/{customerId}/placeOrder")
    public ResponseEntity<Orderr> placeOrder(@PathVariable Long customerId) {
        Orderr order = orderService.placeOrder(customerId);
        if (order!=null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
