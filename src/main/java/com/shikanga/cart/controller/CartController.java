package com.shikanga.cart.controller;

import com.shikanga.cart.model.Cart;
import com.shikanga.cart.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/cart")
public class CartController {

    @GetMapping("/{userId}")
    public Cart getCart(@Valid @PathVariable long userId) {

        List<Product> products = new ArrayList<>();

        Product p1 = new Product(1, "keyboard", 250, 2);
        p1.setTotalPrice(p1.getBasePrice() * p1.getQuantity());
        products.add(p1);

        Product p2 = new Product(2, "mouse", 150, 2);
        p2.setTotalPrice(p2.getBasePrice() * p2.getQuantity());
        products.add(p2);

        double totalPrice = products.stream().mapToDouble(Product::getTotalPrice).sum();

        return new Cart(products.size(), totalPrice, products);
    }
}
