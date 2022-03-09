package com.example.mobileaccessoriesbackend.dto.response;

import lombok.Data;

import javax.persistence.Column;

@Data
public class DriverResponse {

    private Long id;
    private String name;
    private String contactNo;
    private String address;
}
