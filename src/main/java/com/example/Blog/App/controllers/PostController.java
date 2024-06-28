package com.example.Blog.App.controllers;


import com.example.Blog.App.PostResponse;
import com.example.Blog.App.dto.PostDto;
import com.example.Blog.App.model.Post;
import com.example.Blog.App.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id){
        return postService.getPost(id);
    }

    @PostMapping("user/{userId}/category/{categoryId}")
    public ResponseEntity<Post> createPost(@RequestBody PostDto postDto,
                                             @PathVariable Long userId,
                                             @PathVariable Long categoryId){
        return ResponseEntity.ok(postService.createPost(postDto,userId,categoryId));
    }

    @PutMapping("/{postId}")
    public PostDto updatePost(@RequestBody PostDto postDto,@PathVariable Long postId){
        return postService.updatePost(postDto,postId);
    }

    @GetMapping
    public PostResponse getPosts(
            @RequestParam(value = "pageNumber",required = false,defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "postId",required = false) String sortBy,
            @RequestParam(value = "order",defaultValue = "asc",required = false) String order){
        return postService.getAllPost(pageNumber, pageSize,sortBy,order);
    }

    @GetMapping("by_user/{id}")
    public List<Post> postByUserId(@PathVariable Long id){
        return postService.getAllPostByUserId(id);
    }
    @GetMapping("by_category/{id}")
    public List<Post> postByCategoryId(@PathVariable Long id){
        return postService.getAllPostsByCategoryId(id);
    }

}
