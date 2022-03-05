package com.example.mobileaccessoriesbackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesAgentRequest {

    private Long id;
    private String name;
    private String username;
    private String contactNo;
    private Long branchId;
}
