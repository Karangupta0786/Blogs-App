package com.example.Blog.App.service;

import com.example.Blog.App.dto.UserDto;
import java.util.List;

public interface UserService {

    public UserDto getById(Long id);

    public List<UserDto> getUsers();

    public Boolean deleteUser(Long id);

    public UserDto createUser(UserDto userDto);

    public String updateUSer(Long id, UserDto userDto);

}
