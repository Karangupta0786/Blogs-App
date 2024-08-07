package com.example.Blog.App.service;

import com.example.Blog.App.PostResponse;
import com.example.Blog.App.dto.PostDto;
import com.example.Blog.App.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {

    Post createPost(PostDto postDto, Long userId,Long categoryId);

    PostDto updatePost(PostDto postDto,Long postId);

    PostDto getPost(Long postId);

    void deletePost(Long postId);

    PostResponse getAllPost(int pageNumber, int pageSize,String sortBy,String order);

    List<Post> getAllPostByUserId(Long id);

    List<Post> getAllPostsByCategoryId(Long id);

    ResponseEntity<List<Post>> searchPosts(String keyword);

}
