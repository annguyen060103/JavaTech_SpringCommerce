package com.example.SpringCommerce.controllers;

import com.example.SpringCommerce.models.*;
import com.example.SpringCommerce.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private BrandService brandService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ShoppingCartServiceImp shoppingCartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("")
    public String showIndex(Model model,
                            @RequestParam(required = false) String keyword,
                            @RequestParam(required = false) Long brandId) {
        List<Product> products;

        if (keyword != null && !keyword.isEmpty()) {
            products = productService.searchByName(keyword);
            model.addAttribute("search_word", keyword);
        } else if (brandId != null) {
            products = brandService.findById(brandId);
        } else {
            products = productService.getAll();
        }



        List<Brand> brands = brandService.getAll();

        model.addAttribute("products", products);
        model.addAttribute("brands", brands);
        model.addAttribute("quantity_cart", shoppingCartService.getCount());

        return "index";
    }

    @GetMapping("/shopping-cart")
    public String showShoppingCart(Model model) {
        model.addAttribute("cart_products", shoppingCartService.getAllProducts());
        model.addAttribute("quantity_cart", shoppingCartService.getCount());
        model.addAttribute("total", shoppingCartService.getAmount());
        return "shopping-cart";
    }

    @GetMapping("/shopping-cart/add/{id}")
    public String addCart(@PathVariable("id") Long id) {
        Product foundProduct = productService.findById(id);

        CartProduct newCartProduct = new CartProduct();
        newCartProduct.setProductId(foundProduct.getId());
        newCartProduct.setProductDTO(new ProductDTO(foundProduct));
        shoppingCartService.add(newCartProduct);

        return "redirect:/shopping-cart";
    }

    @GetMapping("/shopping-cart/minus/{id}")
    public String minusCart(@PathVariable("id") Long id) {
        Product foundProduct = productService.findById(id);

        CartProduct newCartProduct = new CartProduct();
        newCartProduct.setProductId(foundProduct.getId());
        newCartProduct.setProductDTO(new ProductDTO(foundProduct));
        shoppingCartService.minus(newCartProduct);

        return "redirect:/shopping-cart";
    }

    @GetMapping("/shopping-cart/remove/{id}")
    public String removeCart(@PathVariable("id") Long id) {
        shoppingCartService.remove(id);

        return "redirect:/shopping-cart";
    }

    @GetMapping("checkout")
    public String showFormCheckOut(Model model){
        model.addAttribute("cart_products", shoppingCartService.getAllProducts());
        model.addAttribute("quantity_cart", shoppingCartService.getCount());
        model.addAttribute("total", shoppingCartService.getAmount());
        return "checkout";
    }

    private OrderInfoDTO orderInfoDTO = new OrderInfoDTO();

    @PostMapping("checkout")
    public String createOrderDetails(Model model,
                                     @RequestParam String name,
                                     @RequestParam String email,
                                     @RequestParam String phone,
                                     @RequestParam String address
                                     ) {
        String randomCode = orderInfoDTO.randomCode();
        orderInfoDTO.setFullName(name);
        orderInfoDTO.setEmail(email);
        orderInfoDTO.setPhoneNumber(phone);
        orderInfoDTO.setAddress(address);
        orderInfoDTO.setTotal(shoppingCartService.getAmount());
        orderInfoDTO.setCartProductList(shoppingCartService.getAllProducts().stream().toList());

        orderService.addOrder(orderInfoDTO, randomCode);
        orderDetailService.addOrderDetail(orderInfoDTO, randomCode);

        return "redirect:/order-success";
    }

    @GetMapping("order-success")
    public String showOrderSuccess(Model model){
        model.addAttribute("cart_products", shoppingCartService.getAllProducts());
        model.addAttribute("quantity_cart", shoppingCartService.getCount());
        model.addAttribute("total", shoppingCartService.getAmount());
        model.addAttribute("orderInfoDTO", orderInfoDTO);
        shoppingCartService.clear();
        return "order-success";
    }

}
