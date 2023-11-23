package com.example.SpringCommerce.controllers;

import com.example.SpringCommerce.models.Brand;
import com.example.SpringCommerce.models.ResponseObject;
import com.example.SpringCommerce.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/Brands")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping("")
    List<Brand> getAll() {
        return brandService.getAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        return brandService.getById(id);
    }

    // Post
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Brand newBrand) {
        return brandService.addBrand(newBrand);
    }

    @PutMapping("{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Brand newBrand, @PathVariable Long id) {
        return brandService.updateBrand(newBrand, id);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteBrand(@PathVariable Long id) {
        return brandService.deleteBrand(id);
    }
}
