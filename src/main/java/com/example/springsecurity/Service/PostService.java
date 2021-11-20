package com.example.springsecurity.Service;

import com.example.springsecurity.Dao.Post;

import java.util.List;

public interface PostService {

    Post addPost(Post post);

    List<Post> getPostByUserId(Long userid);

    Post getPostByUserName(String username);
}
