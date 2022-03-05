package com.example.mobileaccessoriesbackend.services.interfaces;

import com.example.mobileaccessoriesbackend.dto.request.UserRequest;
import com.example.mobileaccessoriesbackend.dto.response.UserResponse;
import com.example.mobileaccessoriesbackend.entity.User;

import java.util.List;

public interface IUserService {

    UserResponse addUser(UserRequest userRequest);

    List<UserResponse> getAllUsers();

    User getUserById(Long id);

    UserResponse updateUserDetails(UserRequest userRequest);

    Boolean deleteUser(Long id);
}
