package com.example.springsecurity;

import com.example.springsecurity.Dao.Role;
import com.example.springsecurity.Dao.Users;
import com.example.springsecurity.Dto.UserDto;
import com.example.springsecurity.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.beans.BeanProperty;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

//    @Bean
//    CommandLineRunner run(UserService userService) {
//        return args -> {
//            userService.saveRole(new Role(null,"ROLE_USER"));
//            userService.saveRole(new Role(null,"ROLE_MODERATOR"));
//            userService.saveRole(new Role(null, "ROLE_SENIOR_MODERATOR"));
//            userService.saveRole(new Role(null,"ROLE_ADMIN"));
//            userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));
//
////            userService.saveUser(new Users(
////                    null,
////                    "username",
////                    "FirstName",
////                    "LastNam
////                    "something@gmail.com",
////                    "123456",
////                    "contact",
////                    Collections.EMPTY_SET,
////                    Collections.EMPTY_LIST,
////                    Collections.EMPTY_LIST
////            ));
//
//
//            userService.saveUser(new Users(new UserDto("username", "FirstName1", "LastName1", "something1@gmail.com", "123456", "contact1"
//            )));
//            userService.saveUser(new Users(new UserDto("username1", "FirstName1", "LastName1", "something1@gmail.com", "123456", "contact1"
//            )));
//
////            userService.addRoleToUser("username","ROLE_USER");
////            userService.addRoleToUser("username","ROLE_MODERATOR");
//
//        };
//    }

}
