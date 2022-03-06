package com.example.mobileaccessoriesbackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private Long productId;
    private String productName;
    private Double sellingPrice;
    private MultipartFile imgUrl;
    private Long supplierId;
}
