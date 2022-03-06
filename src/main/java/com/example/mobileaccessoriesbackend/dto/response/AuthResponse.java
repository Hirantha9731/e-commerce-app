package com.example.mobileaccessoriesbackend.dto.response;

import com.example.mobileaccessoriesbackend.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String token;
    private String userId;
    private String name;
    private UserType userType;
}
 