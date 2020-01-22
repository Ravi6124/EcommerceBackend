package com.example.demoSpringLoginMicroService.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@Entity
@Table(name="CUSTOMER")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    @Column(name="user_id")
    private int userId;
    private String emailAddress;
    private String password;
    private String role;
}
