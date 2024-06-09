package com.example.Blog.App.impl;

import com.example.Blog.App.dto.UserDto;
import com.example.Blog.App.model.User;
import com.example.Blog.App.repository.UserRepository;
import com.example.Blog.App.service.UserService;
import com.example.Blog.App.util.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto getById(Long id) {
            return userRepository.findById(id).map(this::toDto).orElseThrow(()-> new CustomException("User","id",id));
    }

    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public String deleteUser(Long id) {
        return "Deleted successfully";
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        return toDto(userRepository.save(toUser(userDto)));
    }

    public UserDto toDto(User user){
        UserDto user_dto = new UserDto();
        user_dto.setId(user.getId());
        user_dto.setName(user.getName());
        user_dto.setEmail(user.getEmail());
        user_dto.setPassword(user.getPassword());
        user_dto.setAbout(user.getAbout());
        return user_dto;
    }
    public User toUser(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        return user;
    }




}
