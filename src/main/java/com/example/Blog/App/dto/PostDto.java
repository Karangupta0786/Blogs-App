package com.example.Blog.App.dto;

import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    private Set<CommentDto> comments = new HashSet<>();
}
