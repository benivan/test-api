package com.example.springsecurity.Service;

import com.example.springsecurity.Dao.Role;
import com.example.springsecurity.Dao.Users;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;


public interface UserService {

    Users saveUser(Users user);

    Role saveRole(Role role);

    ResponseEntity<?> addRoleToUser(String userName, String roleName);

    Users getUser(String userName);

    Role getRole(String roleName);

    List<Users> getAllUsers();

    List<Role> getAllRoles();

}
