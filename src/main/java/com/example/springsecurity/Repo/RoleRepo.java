package com.example.springsecurity.Repo;

import com.example.springsecurity.Dao.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findRolesByName(String roleName);
}
