package com.example.springsecurity.Service;

import com.example.springsecurity.Dao.Role;
import com.example.springsecurity.Dao.Users;

import java.util.List;


public interface UserService {

    Users saveUser(Users user);

    Role saveRole(Role role);

    void addRoleToUser(String userName, String roleName);

    Users getUser(String userName);

    Role getRole(String roleName);

    List<Users> getAllUsers();

    List<Role> getAllRoles();

}
