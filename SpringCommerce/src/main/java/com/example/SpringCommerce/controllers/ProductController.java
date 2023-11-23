package com.example.SpringCommerce.controllers;

import com.example.SpringCommerce.models.Brand;
import com.example.SpringCommerce.models.Product;
import com.example.SpringCommerce.models.ProductDTO;
import com.example.SpringCommerce.models.ResponseObject;
import com.example.SpringCommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/Products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        return productService.getById(id);
    }

    // Post
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody ProductDTO newProduct) {
        return productService.addProduct(newProduct);
    }

    @PutMapping("{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        return productService.updateProduct(newProduct, id);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteBrand(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }
}
