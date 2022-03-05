package com.example.mobileaccessoriesbackend.services.interfaces;

import com.example.mobileaccessoriesbackend.entity.User;

import java.util.List;

public interface IUserService {

    User addUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    User getUserByUsername(String username);

}
