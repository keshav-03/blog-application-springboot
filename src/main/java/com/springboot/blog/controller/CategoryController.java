package com.springboot.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.CategoryDTO;
import com.springboot.blog.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.*;

@RestController
@RequestMapping("/api/v1/categories")
@Tag(
    name = "CRUD REST APIs for Category Resource"
)
public class CategoryController {
    
    private CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //add category rest api
    @Operation(
        summary = "Create Category REST API",
        description = "Create Category REST API is used to create a category into database"
    )
    @ApiResponse(
        responseCode = "201",
        description = "Http Status 201 CREATED"
    )
    @SecurityRequirement(
        name = "Bear Authentication"
    )
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO savedCategory = categoryService.addCategory(categoryDTO);

        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    //build get category rest api
    @Operation(
        summary = "Get Category REST API",
        description = "Get Category REST API is used to get category of that particular from the database"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Http Status 201 SUCCESS"
    )
    @GetMapping("{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable("id") Long categoryId) {
        CategoryDTO categoryDTO = categoryService.getCategory(categoryId);
        return ResponseEntity.ok(categoryDTO);
    }

    //get all categories rest api
    @Operation(
        summary = "GetALL Category REST API",
        description = "GetAll Category REST API is used to fetch all the categories from the database"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Http Status 201 SUCCESS"
    )
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    //update category rest api
    @Operation(
        summary = "Update Category REST API",
        description = "Update Category REST API is used to update the category of that particular category by using id in the database"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Http Status 201 SUCCESS"
    )
    @SecurityRequirement(
        name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO, 
                                                    @PathVariable("id") Long categoryId) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryDTO, categoryId));
    }

    //delete category rest api
    @Operation(
        summary = "Delete Category REST API",
        description = "Delete Category REST API is used to  delete category by id from the database"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Http Status 201 SUCCESS"
    )
    @SecurityRequirement(
        name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("Category deleted successfully");
    }
}
