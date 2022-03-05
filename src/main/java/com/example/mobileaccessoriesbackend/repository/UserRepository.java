package com.example.mobileaccessoriesbackend.repository;

import com.example.mobileaccessoriesbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
