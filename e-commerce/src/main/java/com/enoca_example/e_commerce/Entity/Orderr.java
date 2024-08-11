package com.enoca_example.e_commerce.Entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
public class Orderr extends BaseEntity {

    String orderCode;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "orderr", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> items;

    private double totalPrice;

}

