package com.example.Blog.App.controllers;

import com.example.Blog.App.dto.CategoryDto;
import com.example.Blog.App.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> get(@PathVariable Long id){
        CategoryDto categoryDto = categoryService.getCategory(id);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAll(){
        return new ResponseEntity<>(categoryService.getAll(),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> add(@Valid @RequestBody CategoryDto categoryDto){
        try {
            return new ResponseEntity<>(categoryService.addCategory(categoryDto),HttpStatus.OK);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> update(@Validated @RequestBody CategoryDto categoryDto, @PathVariable Long id){
        return new ResponseEntity<>(categoryService.update(categoryDto,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>("Category Deleted at id: "+id,HttpStatus.OK);
    }

}
