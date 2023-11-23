package com.example.SpringCommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter @Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "color", nullable = false, length = 30)
    private String color;

    @Column(name = "size", nullable = false)
    private int size;

    @Column(name = "image", nullable = false, length = 30)
    private String image;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    @JsonIgnore
    private Brand brand;

    public Product(String name, Long price, String color, int size, String image) {
        this.name = name;
        this.price = price;
        this.color = color;
        this.size = size;
        this.image = image;
    }

}
