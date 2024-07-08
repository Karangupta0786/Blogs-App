package com.example.Blog.App.impl;

import com.example.Blog.App.dto.CommentDto;
import com.example.Blog.App.dto.UserDto;
import com.example.Blog.App.model.Comment;
import com.example.Blog.App.model.Post;
import com.example.Blog.App.model.User;
import com.example.Blog.App.repository.CommentRepo;
import com.example.Blog.App.repository.PostRepository;
import com.example.Blog.App.repository.UserRepository;
import com.example.Blog.App.service.CommentService;
import com.example.Blog.App.util.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CommentDto addComment(String content, Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(()->new CustomException("Post","id",postId));
        User user = userRepository.findById(userId).orElseThrow(()-> new CustomException("User", "Id",userId));

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setPost(post);
        comment.setUser(user);


        return commentToDto(commentRepo.save(comment));
    }

    @Override
    public void delete(Long id) {
        commentRepo.findById(id).orElseThrow(()-> new CustomException("Comment","id",id));
    }

    public CommentDto commentToDto(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setUserName(modelMapper.map(comment.getUser(),UserDto.class).getName());
        commentDto.setId(comment.getId());
        commentDto.setContent(comment.getContent());
        System.out.println("COMMENT is: "+ comment.getContent()+" user: "+ comment.getUser());
        System.out.println("COMMENT_DTO is: "+commentDto.getContent()+" user: "+ commentDto.getUserName());

        return commentDto;
    }
}
