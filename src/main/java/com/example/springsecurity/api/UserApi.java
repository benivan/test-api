package com.example.springsecurity.api;

import com.example.springsecurity.Dao.Role;
import com.example.springsecurity.Dao.Users;
import com.example.springsecurity.Service.UserService;
import com.example.springsecurity.utlil.RoleToUserForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserApi {
    private final UserService userService;

    @GetMapping("/allUsers")
    public ResponseEntity<List<Users>> getAllUser() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<Users> saveUser(@RequestBody Users users) {
        return ResponseEntity.ok().body(userService.saveUser(users));
    }


    @GetMapping("/user/id/")
    public ResponseEntity<Users> getUserFromUserName(@RequestParam String username){
        return ResponseEntity.ok().body(userService.getUser(username));
    }

    @PostMapping("/user/add/role")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){
        userService.addRoleToUser(form.getUserName(),form.getRole());
        return ResponseEntity.ok().build();
    }


}

