package com.example.mobileaccessoriesbackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private Long productId;
    private String productName;
    private Double sellingPrice;
    private String imgUrl;
    private Long supplierId;
}
