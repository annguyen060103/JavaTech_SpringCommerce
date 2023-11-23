package com.example.SpringCommerce.services;

import com.example.SpringCommerce.models.CartProduct;

import java.util.Collection;
import java.util.Map;

public interface ShoppingCartService {
    void add(CartProduct product);
    void minus(CartProduct product);
    void remove(Long id);
    void clear();
    Collection<CartProduct> getAllProducts();
    Map<Long, CartProduct> getAllProductss();
    int getCount();
    Long getAmount();
}
