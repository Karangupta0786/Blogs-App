package com.example.Blog.App.controllers;


import com.example.Blog.App.PostResponse;
import com.example.Blog.App.dto.PostDto;
import com.example.Blog.App.model.Post;
import com.example.Blog.App.service.FileService;
import com.example.Blog.App.service.PostService;
import com.example.Blog.App.util.AppConstants;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @GetMapping("/{id}")
    public PostDto getPostById(@PathVariable Long id){
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

    @GetMapping("/all")
    public PostResponse getPosts(
            @RequestParam(value = "pageNumber",required = false,defaultValue = AppConstants.PAGE_NUMBER) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
            @RequestParam(value = "order",defaultValue = AppConstants.SORT_DIR,required = false) String order){
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

    @GetMapping("search/{key}")
    public ResponseEntity<List<Post>> searchPost(@PathVariable String key){
        return postService.searchPosts(key);
    }

    // post image upload

    @PostMapping("/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadImage(
            @RequestParam("image")MultipartFile image,
            @PathVariable Long postId
            ) throws IOException {

        PostDto postDto = postService.getPost(postId);
        String fileName = fileService.uploadImage(path,image);

        System.out.println("fileName: "+fileName);
        postDto.setImageName(fileName);
        System.out.println("post dto: "+postDto);
        PostDto updatedPost = postService.updatePost(postDto,postId);
        System.out.println("updated post: "+updatedPost);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    // Method to serve the files

    @GetMapping(value = "/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable String imageName,
            HttpServletResponse response
    ) throws IOException{

        InputStream resource = fileService.getResource(path,imageName);

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);

        StreamUtils.copy(resource,response.getOutputStream());


    }



}
