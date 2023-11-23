package com.example.SpringCommerce.repositories;

import com.example.SpringCommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
    @Query("SELECT s FROM Product s WHERE s.name like %:name%")
    List<Product> searchByName(String name);
    boolean existsByName(String name);
}
