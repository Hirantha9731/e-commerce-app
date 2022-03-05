package com.example.mobileaccessoriesbackend.controller;

import com.example.mobileaccessoriesbackend.dto.response.CategoryResponse;
import com.example.mobileaccessoriesbackend.dto.response.StandardResponse;
import com.example.mobileaccessoriesbackend.entity.Category;
import com.example.mobileaccessoriesbackend.services.interfaces.ICategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {

    private ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<CategoryResponse> categories = categoryService.getAllCategories();
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "Categories fetch successful",
                categories));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id){

        Category category = categoryService.findCategoryById(id);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "Category fetch successful",
                category
        ));
    }
}
