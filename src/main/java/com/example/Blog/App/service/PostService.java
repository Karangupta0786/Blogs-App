package com.example.Blog.App.service;

import com.example.Blog.App.dto.PostDto;
import com.example.Blog.App.model.Post;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    Post createPost(PostDto postDto, Long userId,Long categoryId);

    PostDto updatePost(PostDto postDto,Long postId);

    Post getPost(Long postId);

    void deletePost(Long postId);

    List<PostDto> getAllPost(int pageNumber, int pageSize);

    List<Post> getAllPostByUserId(Long id);

    List<Post> getAllPostsByCategoryId(Long id);

    List<Post> searchPosts(String keyword);

}
