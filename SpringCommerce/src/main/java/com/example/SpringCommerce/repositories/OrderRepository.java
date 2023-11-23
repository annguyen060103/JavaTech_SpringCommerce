package com.example.SpringCommerce.repositories;

import com.example.SpringCommerce.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByRandomCode(String randomCode);
}
