package com.example.Blog.App.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    Long id;

    @NotEmpty
    String name;
    @NotEmpty
    String password;
    @NotEmpty
    @Email
    String email;
    @NotBlank
    String about;

}
