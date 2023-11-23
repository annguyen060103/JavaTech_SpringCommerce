package com.example.SpringCommerce.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CartProduct {
    private Long productId;
    private ProductDTO productDTO;
    private int quantity = 1;

    public void plusQuantity() {
        this.quantity += 1;
    }

    public void minusQuantity() {
        this.quantity -= 1;
    }
}
