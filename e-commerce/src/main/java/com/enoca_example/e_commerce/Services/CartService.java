package com.enoca_example.e_commerce.Services;

import com.enoca_example.e_commerce.Entity.Cart;
import com.enoca_example.e_commerce.Entity.CartItem;
import com.enoca_example.e_commerce.Entity.Customer;
import com.enoca_example.e_commerce.Entity.Product;
import com.enoca_example.e_commerce.Repository.CartItemRepository;
import com.enoca_example.e_commerce.Repository.CartRepository;
import com.enoca_example.e_commerce.Repository.CustomerRepository;
import com.enoca_example.e_commerce.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class CartService {
    @Autowired
    private final CartRepository cartRepository;
    @Autowired
    private final CartItemRepository cartItemRepository;
    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final  ProductRepository productRepository;


    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    //kontrol edildi
    public Cart getCart(Long id){
        return cartRepository.findById(id).orElse(null);
    }

    public Cart updateCart(Long customerId){
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer==null)
            return null;//customer bulunamadı

        Cart cart = customer.getCart();

        ArrayList<CartItem> trash = new ArrayList<>();
        for(CartItem cartItem : cart.getItems()){
            int stock = cartItem.getProduct().getStock();
            int quantity = cartItem.getQuantity();
            for(;quantity>stock;quantity--);
            if(quantity==0)
                trash.add(cartItem);
            else
                cartItem.setQuantity(quantity);
        }
        cart.getItems().removeAll(trash);

        return cartRepository.save(cart);
    }

    //kontrol edildi
    public void emptyCart(Long id){
        Cart cart = cartRepository.findById(id).orElse(null);
        if (cart != null) {
            cart.getItems().clear();
            cart.setTotalPrice(0.0);
            cartRepository.save(cart);
        }
    }

    //kontrol edildi
    public Cart addProductToCart(Long customerId, Long productId){
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer == null) return null;//kullanıcı yok

        Product product = productRepository.findById(productId).orElse(null);
        if(product == null) return null;// ürün bulunamadı

        if(product.getStock()==0)return null;//stokta ürün yok

        Cart cart = customer.getCart();

        Set<CartItem> items =  cart.getItems();

        boolean flag=false;
        for(CartItem item : items){//item sepet içinde varsa
           if(item.getProduct().getId() == productId) {
               if(product.getStock()<=item.getQuantity())return null;//stock tan fazla talep var
               item.setQuantity(item.getQuantity()+1);//adet sayısını 1 artır
               cart.setTotalPrice(cart.getTotalPrice()+item.getProduct().getPrice());//fiyatı güncelle
               flag=true;
               break;
           }
        }

        if(!flag){//cart içinde böyle bir item yoksa
            CartItem cartItem = new CartItem();
            cartItem.setCart(customer.getCart());
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
            cart.setTotalPrice(cart.getTotalPrice()+product.getPrice());//fiyatı güncelle
            items.add(cartItem);
        }

        return cartRepository.save(customer.getCart());
    }

    //kontrol edildi
    public Cart removeProductFromCart(Long customerId, Long productId){
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer == null ) return null;//kullanıcı yok

        Product product = productRepository.findById(productId).orElse(null);
        if(product == null ) return null;//ürün yok

        Cart cart = customer.getCart();

        Set<CartItem> items = cart.getItems();

        for(CartItem cartItem : items){//item cart içinde varsa
            if(Objects.equals(cartItem.getProduct().getId(), productId)) {
                cartItem.setQuantity(cartItem.getQuantity()-1);
                cart.setTotalPrice(cart.getTotalPrice()-cartItem.getProduct().getPrice());
                if(cartItem.getQuantity()==0){
                    items.remove(cartItem);
                }
                break;
            }
        }

        return cartRepository.save(customer.getCart());
    }
}
