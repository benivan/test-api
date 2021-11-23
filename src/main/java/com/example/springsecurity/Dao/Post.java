package com.example.springsecurity.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String body;
    private String userHandle;
    private String mediaUrl ;
    private LocalDateTime createdAt;
    private Integer likeCount;
    private Integer unlikeCount;
    private String imageUrl;
    private Integer commentCount;
    private Boolean isLiked;
    @ManyToOne(fetch = FetchType.LAZY)
    private Users user;
    @OneToMany(fetch = FetchType.LAZY)
    private Set<Like> likes = new HashSet<>();
    @OneToMany(fetch = FetchType.LAZY)
    private  List<Comment> comments = new ArrayList<>();

}
