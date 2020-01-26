package com.example.demoSpringLoginMicroService.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="LOGIN_HISTORY")
@Getter
@Setter
public class LoginHistory {

    @Id
    @GeneratedValue
    @Column(name = "login_id")
    private int loginId;
    private int userId;
    private String loginTime;
    private String loginSource;
    private String type;
}
