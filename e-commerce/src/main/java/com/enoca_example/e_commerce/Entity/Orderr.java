package com.enoca_example.e_commerce.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
public class Orderr extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "orderr", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> items;

    private double totalPrice;
/*
    @PrePersist
    @PreUpdate
    public void updateTotalPrice() {
        totalPrice = items.stream().mapToDouble(item -> item.getPurchasePrice() * item.getQuantity()).sum();
    }*/
}

