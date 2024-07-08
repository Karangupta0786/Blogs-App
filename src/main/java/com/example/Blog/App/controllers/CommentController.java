package com.example.Blog.App.controllers;

import com.example.Blog.App.dto.CommentDto;
import com.example.Blog.App.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/{postId}")
    public CommentDto addComment(@RequestBody CommentDto commentDto,
                              @PathVariable Long postId,
                              @RequestParam Long userId){
        return commentService.addComment(commentDto.getContent(),postId, userId);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id){
        commentService.delete(id);
    }

}
