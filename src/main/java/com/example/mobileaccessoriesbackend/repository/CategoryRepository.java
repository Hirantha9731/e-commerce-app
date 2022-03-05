package com.example.mobileaccessoriesbackend.repository;

import com.example.mobileaccessoriesbackend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
