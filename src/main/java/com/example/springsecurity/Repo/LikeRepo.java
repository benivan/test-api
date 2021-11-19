package com.example.springsecurity.Repo;

import com.example.springsecurity.Dao.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepo extends JpaRepository<Like,Long> {
    Like findLikesByLikedPKId(Long likeId);
}
