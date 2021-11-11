package com.example.springsecurity.Service;

import com.example.springsecurity.Dao.Role;
import com.example.springsecurity.Dao.Users;
import com.example.springsecurity.Repo.RoleRepo;
import com.example.springsecurity.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService{
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    @Override
    public Users saveUser(Users user) {
       return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        Users users = userRepo.findUsersByUserName(userName);
        Role role = roleRepo.findRolesByName(userName);
        users.getRoles().add(role);
    }

    @Override
    public Users getUser(String userName) {
        return userRepo.findUsersByUserName(userName);
    }

    @Override
    public Role getRole(String roleName) {
        return roleRepo.findRolesByName(roleName);
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepo.findAll();
    }
}
