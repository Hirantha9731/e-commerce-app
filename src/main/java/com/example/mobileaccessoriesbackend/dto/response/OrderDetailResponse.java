package com.example.mobileaccessoriesbackend.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailResponse {

    private Long id;
    private OrderResponse orderId;
    private ProductResponse productId;
    private int productQt;
}
