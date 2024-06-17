package com.example.Blog.App.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class PostDto {

    Long id;

    private String title;
    private String content;

    private String imageName;
    private Date addedDate;

    private CategoryDto categoryDto;

    private UserDto userDto;
}
