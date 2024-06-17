package com.example.Blog.App.service;

import com.example.Blog.App.dto.PostDto;
import com.example.Blog.App.model.Post;

import java.util.List;

public interface PostService {

    Post createPost(PostDto postDto, Long userId,Long categoryId);

    PostDto updatePost(PostDto postDto,Long postId);

    Post getPost(Long postId);

    void deletePost(Long postId);

    List<Post> getAllPost();

    List<Post> getAllPostByUserId(Long id);

    List<Post> getAllPostsByCategoryId(Long id);

    List<Post> searchPosts(String keyword);

}
