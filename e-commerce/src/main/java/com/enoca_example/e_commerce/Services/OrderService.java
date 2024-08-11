package com.enoca_example.e_commerce.Services;

import com.enoca_example.e_commerce.Entity.*;
import com.enoca_example.e_commerce.Repository.CustomerRepository;
import com.enoca_example.e_commerce.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.text.SimpleDateFormat;


@Service
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public List<Orderr> getAllOrdersForCustomer(Long id) {
        return orderRepository.findByCustomerId(id);
    }

    public Orderr getOrderForCode(String orderCode) {
        return orderRepository.findByOrderCode(orderCode);
    }


    public Orderr placeOrder(Long customerId) {
        Customer customer;
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if(optionalCustomer.isPresent())
            customer = optionalCustomer.get();
        else
            return null;//kullanıcı yok

        Cart cart = customer.getCart();
        if (cart.getItems().isEmpty()) return null;//sepet boş
        for (CartItem cartItem : cart.getItems()) {
            if (cartItem.getQuantity() > cartItem.getProduct().getStock())
                return null;//yetersiz stok
        }

        Orderr orderr = new Orderr();
        double totalPrice = 0;
        Set<OrderItem> orderItems = new HashSet<>();
        for (CartItem cartItem : cart.getItems()) {
            if(cartItem.getProduct().getStock()-cartItem.getQuantity()<0)
                return null;//stock yetersiz

            OrderItem orderItem = new OrderItem();
            orderItem.setOrderr(orderr);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPurchasePrice(cartItem.getProduct().getPrice());
            orderItems.add(orderItem);
            int remainingStock = orderItem.getProduct().getStock()-orderItem.getQuantity();
            orderItem.getProduct().setStock(remainingStock);
            totalPrice += orderItem.getPurchasePrice()*orderItem.getQuantity();
        }

        orderr.setCustomer(customer);
        orderr.setItems(orderItems);
        orderr.setTotalPrice(totalPrice);
        orderr.setOrderCode(generateOrderCode());
        return orderRepository.save(orderr);
    }

    private String generateOrderCode() {
        String datePart = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String randomPart = String.format("%04d", new Random().nextInt(10000));
        return "ORD-" + datePart + "-" + randomPart;
    }
}
