package com.enoca_example.e_commerce.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
public class Cart extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> items;

    private double totalPrice;
/*
    @PrePersist
    @PreUpdate
    public void updateTotalPrice() {
        totalPrice = items.stream().mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).sum();
    }*/
}
