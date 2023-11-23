package com.example.SpringCommerce.services;

import com.example.SpringCommerce.models.CartProduct;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SessionScope
@Service
public class ShoppingCartServiceImp implements ShoppingCartService {
    Map<Long, CartProduct> maps = new HashMap<>(); // Cart

    @Override
    public void add(CartProduct product) {
        CartProduct cartProduct = maps.get(product.getProductId());
        if (cartProduct == null) {
            maps.put(product.getProductId(), product);
        } else {
            cartProduct.plusQuantity();
        }
    }

    @Override
    public void minus(CartProduct product) {
        CartProduct cartProduct = maps.get(product.getProductId());
        if (cartProduct != null) {
            cartProduct.minusQuantity();
            if(cartProduct.getQuantity() == 0) {
                remove(cartProduct.getProductId());
            }
        }
    }

    @Override
    public void remove(Long id) {
        maps.remove(id);
    }

    @Override
    public void clear() {
        maps.clear();
    }

    @Override
    public Collection<CartProduct> getAllProducts() {
        return maps.values();
    }

    @Override
    public Map<Long, CartProduct> getAllProductss() {
        return maps;
    }

    @Override
    public int getCount() {
        return maps.values().size();
    }

    @Override
    public Long getAmount() {
        return maps.values().stream()
                .mapToLong(product -> (long) product.getQuantity() * product.getProductDTO().getPrice())
                .sum();
    }
}
