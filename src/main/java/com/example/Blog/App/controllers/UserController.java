package com.example.Blog.App.controllers;

import com.example.Blog.App.dto.UserDto;
import com.example.Blog.App.impl.UserServiceImpl;
import com.example.Blog.App.model.User;
import com.example.Blog.App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserServiceImpl userServiceImpl;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userServiceImpl.toUser(userService.getById(id));
    }

    @PostMapping
    public User post(@RequestBody User user){
        return userServiceImpl.toUser(userService.createUser(userServiceImpl.toDto(user)));
    }

    @GetMapping
    public List<User> getAll(){
        return userService.getUsers().stream().map(l->userServiceImpl.toUser(l)).toList();
    }


}
