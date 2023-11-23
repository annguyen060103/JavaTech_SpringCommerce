package com.example.SpringCommerce.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false, length = 30)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "brand")
    private Set<Product> products = new HashSet<>(0);

    public Brand(String name) {
        this.name = name;
    }
}
