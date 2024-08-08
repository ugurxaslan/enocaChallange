package com.enoca_example.e_commerce.Services;

import com.enoca_example.e_commerce.Entity.Cart;
import com.enoca_example.e_commerce.Entity.CartItem;
import com.enoca_example.e_commerce.Entity.Customer;
import com.enoca_example.e_commerce.Entity.Product;
import com.enoca_example.e_commerce.Repository.CartRepository;
import com.enoca_example.e_commerce.Repository.CustomerRepository;
import com.enoca_example.e_commerce.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class CartService {
    @Autowired
    private final CartRepository cartRepository;
    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final  ProductRepository productRepository;

    public CartService(CartRepository cartRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public Cart getCart(Long id){
        return cartRepository.findById(id).orElse(null);
    }

    public Cart updateCart(Cart cart){
        return cartRepository.save(cart);
    }

    public void emptyCart(Long id){
        Cart cart = cartRepository.findById(id).orElse(null);
        if (cart != null) {
            cart.getItems().clear();
            cart.setTotalPrice(0.0);
            cartRepository.save(cart);
        }
    }

    public Cart addProductToCart(Long customerId, Long productId){
        Optional<Customer> opCustomer = customerRepository.findById(customerId);
        if(opCustomer.isEmpty()) return null;
        Customer customer = opCustomer.get();

        Optional<Product> opProduct = productRepository.findById(productId);
        if(opProduct.isEmpty()) return null;
        Product product = opProduct.get();

        Cart cart = customer.getCart();
        if(cart==null){
            cart = new Cart();
            cart.setCustomer(customer);
            customer.setCart(cart);
        }

        Set<CartItem> items =  cart.getItems();

        boolean flag=false;
        for(CartItem ci : items){//item cart içinde varsa
           if(Objects.equals(ci.getProduct().getId(), productId)) {
               ci.setQuantity(ci.getQuantity()+1);
               flag=true;
               break;
           }
        }

        if(!flag){//cart içinde böyle bir item yoksa
            CartItem cartItem = new CartItem();
            cartItem.setCart(customer.getCart());
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
            items.add(cartItem);

        }

        cart.setItems(items);
        customer.setCart(cart);
        customerRepository.save(customer);

        return cartRepository.save(customer.getCart());
    }

    public Cart removeProductFromCart(Long customerId, Long productId){
        Optional<Customer> opCustomer = customerRepository.findById(customerId);
        if(opCustomer.isEmpty()) return null;
        Customer customer = opCustomer.get();

        Optional<Product> opProduct = productRepository.findById(productId);
        if(opProduct.isEmpty()) return null;


        Cart cart = customer.getCart();
        if(cart==null)return null;

        Set<CartItem> items = cart.getItems();

        for(CartItem ci : items){//item cart içinde varsa
            if(Objects.equals(ci.getProduct().getId(), productId)) {
                ci.setQuantity(ci.getQuantity()-1);
                if(ci.getQuantity()==0)
                    items.remove(ci);
                break;
            }
        }

        cart.setItems(items);
        customer.setCart(cart);
        customerRepository.save(customer);
        return cartRepository.save(customer.getCart());
    }
}
