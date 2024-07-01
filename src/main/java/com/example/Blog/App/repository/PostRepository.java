package com.example.Blog.App.repository;

import com.example.Blog.App.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    @Query(value = "select * from posts where user_id = :userId",nativeQuery = true)
    List<Post> findByUser(Long userId);

    @Query(value = "select * from posts where category_id = :categoryId",nativeQuery = true)
    List<Post> findByCategory(Long categoryId);


    // is it not good to search
    // Use JPQL and wildcards for search
    @Query("SELECT p FROM Post p WHERE p.title LIKE %:key%")
    List<Post> searchPosts(String key);

}
