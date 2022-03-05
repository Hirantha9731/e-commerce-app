package com.example.mobileaccessoriesbackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierResponse {

    private Long supplierId;
    private String username;
    private String supplierName;
    private String brandName;
    private String contactNo;
    private String address;
}
