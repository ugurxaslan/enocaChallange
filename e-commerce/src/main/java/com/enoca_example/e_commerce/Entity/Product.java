package com.enoca_example.e_commerce.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Product extends BaseEntity {

    private String name;
    private double price;
    private int stock;

}
