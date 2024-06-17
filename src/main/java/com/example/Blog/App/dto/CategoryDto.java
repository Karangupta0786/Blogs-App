package com.example.Blog.App.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class CategoryDto {
    private Integer id;

    @NotEmpty(message = "Title can't be empty!!")
    @Size(min = 5, max = 10,message = "Title must have chars between 5 to 10")
    private String title;

    @NotEmpty(message = "description can't be empty!!")
    @Size(min = 8, max = 40,message = "description must have chars between 8 to 40")
    private String description;
}
