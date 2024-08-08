package com.enoca_example.e_commerce.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDTO {
    private String name;
    private double price;
    private int stock;
}
