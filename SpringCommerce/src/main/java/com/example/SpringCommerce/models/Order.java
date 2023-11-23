package com.example.SpringCommerce.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "OderInfo")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fullName", nullable = false, length = 255)
    private String fullName;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "phoneNumber", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "address", nullable = false, length = 500)
    private String address;

    @Column(name = "total", nullable    = false)
    private Long total;

    @Column(name = "randomCode", nullable = false)
    private String randomCode;

    // Thêm các trường khác theo yêu cầu của bạn

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private Set<OrderDetail> orderDetails = new HashSet<>(0);

    public Order(String fullName, String email, String phoneNumber, String address, Long total, String randomCode) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.total = total;
        this.randomCode = randomCode;
    }
}
