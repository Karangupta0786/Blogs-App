package com.example.Blog.App.controllers;

import com.example.Blog.App.dto.UserDto;
import com.example.Blog.App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK) ;
    }

    @PostMapping
    public UserDto post(@RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }

    @GetMapping
    public List<UserDto> getAll(){
        return userService.getUsers();
    }


}
