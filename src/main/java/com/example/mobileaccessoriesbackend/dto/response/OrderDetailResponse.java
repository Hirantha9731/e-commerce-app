package com.example.mobileaccessoriesbackend.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailResponse {

    private Long productId;
    private String productName;
    private String description;
    private Double sellingPrice;
    private byte[] imgUrl;
    private int qty;
    private String supplierName;
}
