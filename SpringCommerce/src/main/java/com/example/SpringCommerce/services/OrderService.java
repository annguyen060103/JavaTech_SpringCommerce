package com.example.SpringCommerce.services;

import com.example.SpringCommerce.models.*;
import com.example.SpringCommerce.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    public Order findByRandomCode(String randomCode) {
        return orderRepository.findByRandomCode(randomCode);
    }

    public void addOrder(OrderInfoDTO orderInfoDTO, String randomCode) {
        Order newOrder = new Order();
        newOrder.setFullName(orderInfoDTO.getFullName());
        newOrder.setEmail(orderInfoDTO.getEmail());
        newOrder.setPhoneNumber(orderInfoDTO.getPhoneNumber());
        newOrder.setAddress(orderInfoDTO.getAddress());
        newOrder.setTotal(orderInfoDTO.getTotal());
        newOrder.setRandomCode(randomCode);

        orderRepository.save(newOrder);
    }
}
