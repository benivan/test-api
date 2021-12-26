package com.example.springsecurity.api;

import com.example.springsecurity.Dao.Role;
import com.example.springsecurity.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/role")
public class RoleApi {

    private final UserService userService;

    @PostMapping("/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/role/save").toString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }
}
