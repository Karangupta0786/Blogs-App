package com.example.Blog.App.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name",nullable = false,length = 100)
    String name;
    String password;
    String email;
    String about;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Post> posts = new ArrayList<>();
}
