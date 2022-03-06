package com.example.mobileaccessoriesbackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReStockRequest {

    private Long id;
    private String description;
    private Long productId;
    private Long salesAgentId;
}
