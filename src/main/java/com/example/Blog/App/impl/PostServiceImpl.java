package com.example.Blog.App.impl;

import com.example.Blog.App.PostResponse;
import com.example.Blog.App.dto.PostDto;
import com.example.Blog.App.model.Category;
import com.example.Blog.App.model.Post;
import com.example.Blog.App.model.User;
import com.example.Blog.App.repository.CategoryRepository;
import com.example.Blog.App.repository.PostRepository;
import com.example.Blog.App.repository.UserRepository;
import com.example.Blog.App.service.PostService;
import com.example.Blog.App.util.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
@Component
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Post createPost(PostDto postDto, Long userId,Long categoryId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new CustomException("User","ID",userId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new CustomException("Category","ID",categoryId));

        postDto.setAddedDate(new Date());
        System.out.println(postDto);

        Post post = modelMapper.map(postDto,Post.class);
        post.setCategory(category);
        post.setUser(user);

        Post newPost = postRepository.save(post);
        System.out.println(newPost);
        return newPost;

    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {
        Post existingPost = postRepository.findById(postId).orElseThrow(()-> new CustomException("Post","ID",postId));
        existingPost.setTitle(postDto.getTitle());
        existingPost.setContent(postDto.getContent());
        return modelMapper.map(postRepository.save(existingPost), PostDto.class);
    }


    @Override
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()->new CustomException("Post","Id",postId));
        postRepository.delete(post);
    }

    @Override
    public Post getPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(()-> new CustomException("Post","ID",postId));
    }

    @Override
    public PostResponse getAllPost(int pageNumber, int pageSize, String sortBy,String order) {

        Sort sort = null;
        if (order.equalsIgnoreCase("asc")) {
            sort = Sort.by(sortBy).ascending();
        }
        else {
            sort = Sort.by(sortBy).descending();
        }

        Pageable p = PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> postPage = postRepository.findAll(p);
        List<Post> postList = postPage.getContent();
        List<PostDto> postDtoList = postList
                                    .stream()
                                    .map(l->modelMapper.map(l, PostDto.class))
                                    .toList();
        PostResponse postResponse = new PostResponse();

        postResponse.setContent(postDtoList);
        postResponse.setPageNumber(postPage.getNumber());
        postResponse.setPageSize(postPage.getSize());
        postResponse.setTotalElements(postPage.getTotalElements());
        postResponse.setTotalPages(postPage.getTotalPages());
        postResponse.setPageNumber(postPage.getNumber());
        postResponse.setLastPage(postPage.isLast());

        return postResponse;
    }



    // task-> implement pagination for getAllPostByUserId & getAllPostsByCategoryId.



    @Override
    public List<Post> getAllPostByUserId(Long id) {
        return postRepository.findByUser(id);
    }

    @Override
    public List<Post> getAllPostsByCategoryId(Long id) {
        return postRepository.findByCategory(id);
    }

    public ResponseEntity<List<Post>> searchPosts(String key){
        return ResponseEntity.ok(postRepository.searchPosts(key));
    }

}
