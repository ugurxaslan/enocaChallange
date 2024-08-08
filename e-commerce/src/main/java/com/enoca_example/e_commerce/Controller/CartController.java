package com.enoca_example.e_commerce.Controller;

import com.enoca_example.e_commerce.Entity.Cart;
import com.enoca_example.e_commerce.Services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/carts")
public class  CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{customerId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long customerId) {
        Cart cart = cartService.getCart(customerId);
        return ResponseEntity.ok(cart);
    }

    @PatchMapping
    public ResponseEntity<Cart> updateCart(@RequestBody Cart cart) {
        Cart updatedCart = cartService.updateCart(cart);
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping("/{customerId}/empty")
    public ResponseEntity<Void> emptyCart(@PathVariable Long customerId) {
        cartService.emptyCart(customerId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{customerId}")
    public ResponseEntity<Cart> addProductToCart(
            @PathVariable Long customerId,
            @RequestBody Long productId) {
        Cart updatedCart = cartService.addProductToCart(customerId, productId);
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping("/{customerId}/removeProduct")
    public ResponseEntity<Cart> removeProductFromCart(
            @PathVariable Long customerId,
            @RequestBody Long productId ) {
        Cart updatedCart = cartService.removeProductFromCart(customerId, productId);
        return ResponseEntity.ok(updatedCart);
    }
}