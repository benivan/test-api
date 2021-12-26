package com.example.springsecurity.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.springsecurity.Dao.Role;
import com.example.springsecurity.Dao.Users;
import com.example.springsecurity.Dto.UserDto;
import com.example.springsecurity.Service.UserService;
import com.example.springsecurity.utlil.RoleToUserForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserApi {
    private final UserService userService;

    @GetMapping("/hello")
    public ResponseEntity<?> helloWorld() {
        return ResponseEntity.ok().body("Hello World");
    }


    @GetMapping("/all")
    public ResponseEntity<List<Users>> getAllUser() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PostMapping("/save")
    public ResponseEntity<Users> saveUser(@RequestBody UserDto userDto) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(new Users(userDto)));
    }

    @PostMapping("/sign_up")
    public void singUp(@RequestBody UserDto user, HttpServletRequest request, HttpServletResponse response) throws IOException{
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
            Users savedUser = userService.saveUser(new Users(user));
            String accessToken = JWT.create()
                    .withSubject(savedUser.getUserName())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                    .withClaim("roles", savedUser.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                    .withIssuer(request.getRequestURL().toString())
                    .sign(algorithm);
            String refresh_token = JWT.create()
                    .withSubject(savedUser.getUserName())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                    .withIssuer(request.getRequestURL().toString())
                    .sign(algorithm);
            Map<String,String> tokens = new HashMap<>();
            tokens.put("access_token",accessToken);
            tokens.put("refresh",refresh_token);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(),tokens);
//            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/user/sign_Up").toUriString());
//            return ResponseEntity.created(uri).body(userService.saveUser(users));
        }catch (Exception e){
            response.setHeader("error", e.getMessage());
            response.setStatus(BAD_REQUEST.value());
            Map<String, String> error = new HashMap<>();
            error.put("error_message", e.getMessage());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }

    }


    @GetMapping("/id/")
    public ResponseEntity<Users> getUserFromUserName(@RequestParam String username) {
        return ResponseEntity.ok().body(userService.getUser(username));
    }

    @PostMapping("/addrole")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUserName(), form.getRole());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                Users user = userService.getUser(username);
                String accessToken = JWT.create()
                        .withSubject(user.getUserName())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .withIssuer(request.getRequestURL().toString())
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", accessToken);
                tokens.put("refresh_token", refreshToken);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception e) {
                response.setHeader("error", e.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Invalid refresh token");
        }
    }


}

