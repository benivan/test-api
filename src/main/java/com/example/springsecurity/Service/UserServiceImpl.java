package com.example.springsecurity.Service;

import com.example.springsecurity.Dao.Role;
import com.example.springsecurity.Dao.Users;
import com.example.springsecurity.Repo.RoleRepo;
import com.example.springsecurity.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Override

    public Users saveUser(Users user) {

//            if (!checkUserNameTaken(user.getUserName())){
//
//            }


        return userRepo.save(user);

    }

    private Boolean checkUserNameTaken(String userName) {
        val usersByUserName = userRepo.findUsersByUserName(userName);
        return (Objects.equals(usersByUserName.getUserName(), userName));
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public ResponseEntity<?> addRoleToUser(String userName, String roleName) {
        Optional<Users> optionalUsers = Optional.ofNullable(userRepo.findUsersByUserName(userName));

        if (optionalUsers.isPresent()) {
            Users user = optionalUsers.get();
            Optional<Role> optionalRole = Optional.ofNullable(roleRepo.findRolesByName(roleName));

            if (optionalRole.isPresent()) {
                Role role = optionalRole.get();
                user.getRoles().add(role);
                userRepo.save(user);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("roles added to user.");
            }
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();


//        Users users = userRepo.findUsersByUserName(userName);
//        Role role = roleRepo.findRolesByName(userName);
//        users.getRoles().add(role);
//        userRepo.save(users);
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
