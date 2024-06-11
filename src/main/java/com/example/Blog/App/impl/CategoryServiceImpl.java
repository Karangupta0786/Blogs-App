package com.example.Blog.App.impl;

import com.example.Blog.App.dto.CategoryDto;
import com.example.Blog.App.model.Category;
import com.example.Blog.App.repository.CategoryRepository;
import com.example.Blog.App.service.CategoryService;
import com.example.Blog.App.util.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public CategoryDto getCategory(Long id) {
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() -> new CustomException("Category", "Category Id", id));

        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAll() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream().map(l -> modelMapper.map(l, CategoryDto.class)).toList();
    }

    @Override
    public void deleteCategory(Long id) {
        // first find it's exists or not
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() -> new CustomException("Category", "id", id));
        // delete it
        categoryRepository.delete(category);
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = categoryRepository.save(modelMapper.map(categoryDto, Category.class));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, Long id) {
        Category category = categoryRepository
                            .findById(id)
                            .orElseThrow(() -> new CustomException("Category", "id", id));
        category.setTitle(category.getDescription());
        category.setTitle(categoryDto.getTitle());
        return modelMapper.map(category, CategoryDto.class);
    }
}
