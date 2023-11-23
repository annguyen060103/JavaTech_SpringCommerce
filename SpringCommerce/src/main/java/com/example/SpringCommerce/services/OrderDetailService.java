package com.example.SpringCommerce.services;

import com.example.SpringCommerce.models.CartProduct;
import com.example.SpringCommerce.models.Order;
import com.example.SpringCommerce.models.OrderDetail;
import com.example.SpringCommerce.models.OrderInfoDTO;
import com.example.SpringCommerce.repositories.OrderDetailRepository;
import com.example.SpringCommerce.repositories.OrderRepository;
import com.example.SpringCommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderDetailService {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public void addOrderDetail(OrderInfoDTO orderInfoDTO, String randomCode) {
        Order order = orderRepository.findByRandomCode(randomCode);


        for (CartProduct cartProduct : orderInfoDTO.getCartProductList()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(productService.findById(cartProduct.getProductId()));
            orderDetail.setQuantity(cartProduct.getQuantity());

            orderDetailRepository.save(orderDetail);
        }
    }
}
