package com.example.mobileaccessoriesbackend.services.interfaces;


import com.example.mobileaccessoriesbackend.dto.response.CategoryResponse;
import com.example.mobileaccessoriesbackend.entity.Category;

import java.util.List;

public interface ICategoryService {

    List<CategoryResponse> getAllCategories();

    Category findCategoryById(Long id);
}
