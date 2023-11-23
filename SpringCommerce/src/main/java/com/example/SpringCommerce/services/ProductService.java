package com.example.SpringCommerce.services;

import com.example.SpringCommerce.models.Brand;
import com.example.SpringCommerce.models.Product;
import com.example.SpringCommerce.models.ProductDTO;
import com.example.SpringCommerce.models.ResponseObject;
import com.example.SpringCommerce.repositories.BrandRepository;
import com.example.SpringCommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BrandRepository brandRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public List<Product> searchByName(String name) {
        return productRepository.searchByName(name);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
    public ResponseEntity<ResponseObject> getById(Long id) {
        Optional<Product> fondProduct = productRepository.findById(id);
        return fondProduct.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(
                                "Successful!",
                                "Query product successfully",
                                fondProduct
                        )
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(
                                "Failed",
                                "Can not find product with id = " + id,
                                ""
                        )
                );
    }

    public ResponseEntity<ResponseObject> addProduct(ProductDTO productDTO) {


        Long idBrand = productDTO.getBrand_id();
        Brand foundBrand = brandRepository.findById(idBrand).orElse(null);
//        boolean existsProductByName = productRepository.existsByName(productDTO.getName().trim());

//        if (existsProductByName || foundBrand == null) {
        if (foundBrand == null) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject(
                            "failed!",
                            "Not found brand",
                            ""
                    )
            );
        }

        Product newProduct = new Product();
        newProduct.setName(productDTO.getName());
        newProduct.setPrice(productDTO.getPrice());
        newProduct.setColor(productDTO.getColor());
        newProduct.setSize(productDTO.getSize());
        newProduct.setImage(productDTO.getImage());
        newProduct.setBrand(foundBrand);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(
                        "successful!",
                        "Insert product successfully",
                        productRepository.save(newProduct)
                )
        );
    }

    public ResponseEntity<ResponseObject> updateProduct(Product newProduct, Long id) {
        Product updateProduct= productRepository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setPrice(newProduct.getPrice());
                    product.setColor(newProduct.getColor());
                    product.setSize(newProduct.getSize());
                    product.setImage(newProduct.getImage());
                    return productRepository.save(product);
                }).orElseGet(() -> {
                    newProduct.setId(id);
                    return productRepository.save(newProduct);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(
                        "successful",
                        "Update product successfully",
                        updateProduct
                )
        );
    }

    public ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
        boolean exists = productRepository.existsById(id);
        Optional<Product> delProduct = productRepository.findById(id);

        if (exists && delProduct.isPresent()) {

            productRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(
                            "Successful",
                            "Delete product successfully!",
                            delProduct
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
