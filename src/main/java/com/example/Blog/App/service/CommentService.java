package com.example.Blog.App.service;


import com.example.Blog.App.dto.CommentDto;
import com.example.Blog.App.model.Comment;


public interface CommentService {

    CommentDto addComment(String content, Long postId, Long userId);

    void delete(Long id);
}
