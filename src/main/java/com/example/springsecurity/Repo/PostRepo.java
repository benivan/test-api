package com.example.springsecurity.Repo;

import com.example.springsecurity.Dao.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post,Long> {
    Post findPostById(Long postId);
}
