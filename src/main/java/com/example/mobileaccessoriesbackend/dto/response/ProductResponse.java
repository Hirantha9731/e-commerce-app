package com.example.mobileaccessoriesbackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private Long productId;
    private String productName;
    private Double sellingPrice;
    private byte[] imgUrl;
    private SupplierResponse supplierId;
}
