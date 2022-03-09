package com.example.mobileaccessoriesbackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private String productId;
    private String productName;
    private String description;
    private String sellingPrice;
    private MultipartFile imgUrl;
    private String supplierId;
}
