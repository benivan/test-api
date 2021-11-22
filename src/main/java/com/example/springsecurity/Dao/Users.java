package com.example.springsecurity.Dao;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String contact;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> roles = new HashSet<>();




//    public Users(UserDto userDto){
//        this.userName = userDto.getUserName();
//        this.firstName = userDto.getFirstName();
//        this.lastName = userDto.getLastName();
//        this.email =  userDto.getEmail();
//        this.password = userDto.getPassword();
//        this.contact = userDto.getContact();
//    }


}
