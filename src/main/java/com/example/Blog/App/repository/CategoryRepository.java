package com.example.Blog.App.repository;

import com.example.Blog.App.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
