package com.example.Blog.App.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
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

    List<CommentDto> comments = new ArrayList<>();
}
