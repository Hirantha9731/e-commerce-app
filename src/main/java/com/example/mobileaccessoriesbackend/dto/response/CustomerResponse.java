package com.example.mobileaccessoriesbackend.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String contactNo;
    private String address;
    private String city;
}
