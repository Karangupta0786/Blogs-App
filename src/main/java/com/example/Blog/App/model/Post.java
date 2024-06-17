package com.example.Blog.App.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "posts")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonManagedReference
    private Category category;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

}
