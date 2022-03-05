package com.example.mobileaccessoriesbackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesAgentResponse {

    private Long id;
    private String name;
    private String username;
    private String contactNo;
    private BranchResponse branchId;


}
