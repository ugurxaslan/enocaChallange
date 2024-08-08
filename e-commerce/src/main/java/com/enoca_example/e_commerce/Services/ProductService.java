package com.enoca_example.e_commerce.Services;

import com.enoca_example.e_commerce.DTO.ProductDTO;
import com.enoca_example.e_commerce.Entity.Product;
import com.enoca_example.e_commerce.Repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        super();
        this.productRepository = productRepository;
    }

    public Product createProduct(ProductDTO dto){
        Product product = new Product();
        product.setName(dto.getName());
        product.setStock(dto.getStock());
        product.setPrice(dto.getPrice());
        return productRepository.save(product);
    }

    public Product getProduct(Long productId){
        return productRepository.findById(productId).orElse(null);
    }

    public Product updateProduct(Long productId, ProductDTO dto){

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("not found: productId " + productId));

        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        return  productRepository.save(product);
    }


    public void deleteProduct(Long productId){
        productRepository.deleteById(productId);
    }

}
