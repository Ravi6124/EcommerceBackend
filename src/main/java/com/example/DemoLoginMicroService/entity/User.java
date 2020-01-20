package com.example.DemoLoginMicroService.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="register_user")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    @Column(name="user_id")
    private int userId;

    private String emailAddress;
    private String token;

    private String role;




}
