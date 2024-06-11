package com.example.Blog.App.service;

import com.example.Blog.App.dto.CategoryDto;

import java.util.List;

public interface CategoryService {


    CategoryDto getCategory(Long id);

    List<CategoryDto> getAll();

    void deleteCategory(Long id);

    CategoryDto addCategory(CategoryDto categoryDto);



    CategoryDto update(CategoryDto categoryDto, Long id);
}
