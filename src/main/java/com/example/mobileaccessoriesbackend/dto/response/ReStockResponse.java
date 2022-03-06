package com.example.mobileaccessoriesbackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReStockResponse {

    private Long id;
    private String description;
    private ProductResponse productId;
    private SalesAgentResponse salesAgentId;
}
