package com.example.mobileaccessoriesbackend.dto.request;

import com.example.mobileaccessoriesbackend.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private Long id;
    private String username;
    private String email;
    private String contactNumber;
    private String password;
    private UserType userType;
}
