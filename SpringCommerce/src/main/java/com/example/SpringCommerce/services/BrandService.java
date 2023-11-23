package com.example.SpringCommerce.services;

import com.example.SpringCommerce.models.Brand;
import com.example.SpringCommerce.models.Product;
import com.example.SpringCommerce.models.ResponseObject;
import com.example.SpringCommerce.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> getAll() {
        return brandRepository.findAll();
    }
    public List<Product> findById(Long id) {
        Brand fondBrand = brandRepository.findById(id).orElse(null);
        List<Product> productList = new ArrayList<>(fondBrand.getProducts());
        return productList;
    }

    public ResponseEntity<ResponseObject> getById(Long id) {
        Optional<Brand> fondProduct = brandRepository.findById(id);
        return fondProduct.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(
                                "Successful!",
                                "Query brand successfully",
                                fondProduct
                        )
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(
                                "Failed",
                                "Can not find brand with id = " + id,
                                ""
                        )
                );
    }

    public ResponseEntity<ResponseObject> addBrand(Brand newBrand) {
        Brand foundProducts = brandRepository.findByName(newBrand.getName().trim());
        return foundProducts != null ?
                ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                        new ResponseObject(
                                "failed!",
                                "Brand name already taken",
                                ""
                        )
                ) :
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(
                                "successful!",
                                "Insert brand successfully",
                                brandRepository.save(newBrand)
                        )
                );
    }

    public ResponseEntity<ResponseObject> updateBrand(Brand newBrand, Long id) {
        Brand updateBrand = brandRepository.findById(id)
                .map(brand -> {
                    brand.setName(newBrand.getName());
                    return brandRepository.save(brand);
                }).orElseGet(() -> {
                    newBrand.setId(id);
                    return brandRepository.save(newBrand);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(
                        "successful",
                        "Update brand successfully",
                        updateBrand
                )
        );
    }

    public ResponseEntity<ResponseObject> deleteBrand(@PathVariable Long id) {
        boolean exists = brandRepository.existsById(id);
        Optional<Brand> delBrand = brandRepository.findById(id);

        if (exists && delBrand.isPresent()) {

            brandRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(
                            "Successful",
                            "Delete brand successfully!",
                            delBrand
                    )
            );
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(
                        "Failed",
                        "Cannot find product to delete!",
                        ""
                )
        );
    }
}
