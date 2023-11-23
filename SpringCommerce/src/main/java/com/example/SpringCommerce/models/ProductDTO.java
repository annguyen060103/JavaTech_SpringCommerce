package com.example.SpringCommerce.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private String name;
    private Long price;
    private String color;
    private int size;
    private String image;
    private Long brand_id;

    public ProductDTO(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.color = product.getColor();
        this.size = product.getSize();
        this.image = product.getImage();
        this.brand_id = product.getBrand().getId();
    }
}
