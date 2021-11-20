package com.example.springsecurity.Repo;

import com.example.springsecurity.Dao.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users,Long> {
    Users findUsersByUserName(String userName);
    Users findUsersById(Long userId);
}
