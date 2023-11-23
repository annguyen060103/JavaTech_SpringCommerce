package com.example.SpringCommerce.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
public class OrderInfoDTO {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private Long total;
    private List<CartProduct> cartProductList;

    public String randomCode()  {
            // Lấy ngày hiện tại
            Date currentDate = new Date();

            // Định dạng ngày thành chuỗi không dấu
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String formattedDate = dateFormat.format(currentDate);

            // Sinh số ngẫu nhiên
            Random random = new Random();
            int randomValue = random.nextInt(10000); // Thay đổi phạm vi theo yêu cầu

            // Kết hợp ngày và số ngẫu nhiên để tạo mã
            String uniqueCode = formattedDate + String.format("%04d", randomValue);

            return uniqueCode;
    }
}
