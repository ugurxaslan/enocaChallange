package com.enoca_example.e_commerce.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class OrderItem extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orderr orderr;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;
    private double purchasePrice;

    @PrePersist
    protected void onBuy(){
        purchasePrice = product.getPrice();
    }
}
