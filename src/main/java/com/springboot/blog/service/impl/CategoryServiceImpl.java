package com.springboot.blog.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.springboot.blog.entity.Category;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CategoryDTO;
import com.springboot.blog.repository.CategoryRepository;
import com.springboot.blog.service.CategoryService;
import java.util.stream.*;;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;


    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        Category savedCategory = categoryRepository.save(category);

        return modelMapper.map(savedCategory, CategoryDTO.class);
    }


    @Override
    public CategoryDTO getCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
            () -> new ResourceNotFoundException("Category", "id", categoryId)
        );

        return modelMapper.map(category, CategoryDTO.class);
    }


    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream().map((category) -> modelMapper.map(category, CategoryDTO.class))
            .collect(Collectors.toList());
    }


    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
            () -> new ResourceNotFoundException("Category", "id", categoryId)
        );

        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setId(categoryId);

        Category updateCategory = categoryRepository.save(category);
        return modelMapper.map(updateCategory, CategoryDTO.class);
    }


    @Override
    public void deleteCategory(Long categoryId) {
       Category category = categoryRepository.findById(categoryId).orElseThrow(
            () -> new ResourceNotFoundException("Category", "id", categoryId)
        );

        categoryRepository.delete(category);
    }
    
}
