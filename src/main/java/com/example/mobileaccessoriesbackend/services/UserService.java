package com.example.mobileaccessoriesbackend.services;

import com.example.mobileaccessoriesbackend.entity.User;
import com.example.mobileaccessoriesbackend.exceptions.ResourceNotFoundException;
import com.example.mobileaccessoriesbackend.repository.UserRepository;
import com.example.mobileaccessoriesbackend.services.interfaces.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id : " + id));

        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }
}
