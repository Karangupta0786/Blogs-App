package com.example.Blog.App.controllers;

import com.example.Blog.App.dto.UserDto;
import com.example.Blog.App.service.UserService;
import com.example.Blog.App.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public UserDto post(@Validated @RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }

    @GetMapping
    public List<UserDto> getAll(){
        return userService.getUsers();
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id,@Validated @RequestBody UserDto userDto){
        return userService.updateUSer(id,userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        boolean status = userService.deleteUser(id);
        if (status){
            return new ResponseEntity<>(new ApiResponse("User Deleted Successfully", true),HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse("User not found at this index",false),HttpStatus.NOT_FOUND);
    }

}
