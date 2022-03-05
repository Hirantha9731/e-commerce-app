package com.example.mobileaccessoriesbackend.services;

import com.example.mobileaccessoriesbackend.dto.response.BranchResponse;
import com.example.mobileaccessoriesbackend.dto.response.CategoryResponse;
import com.example.mobileaccessoriesbackend.entity.Branch;
import com.example.mobileaccessoriesbackend.entity.Category;
import com.example.mobileaccessoriesbackend.exceptions.ResourceNotFoundException;
import com.example.mobileaccessoriesbackend.repository.CategoryRepository;
import com.example.mobileaccessoriesbackend.services.interfaces.ICategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    /**
     * Get All Categories
     * @return
     */
    @Override
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(this::response).collect(Collectors.toList());
    }

    /**
     * Find Category By Identifier
     * @param id
     * @return
     */
    @Override
    public Category findCategoryById(Long id) {
        return  categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Category not exist with id : "+ id));
    }

    /**
     * @param category
     * @return
     */
    public CategoryResponse response(Category category){

        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setCategoryName(category.getCategoryName());
        return response;
    }
}
