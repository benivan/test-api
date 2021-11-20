package com.example.springsecurity.Repo;

import com.example.springsecurity.Dao.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Long> {
    Comment getCommentById(Long CommentId);
}
