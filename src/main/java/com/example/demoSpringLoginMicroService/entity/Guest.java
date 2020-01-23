package com.example.demoSpringLoginMicroService.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.http.annotation.Experimental;

import javax.persistence.*;

@Entity
@Table(name="GUEST")
@Getter
@Setter
public class Guest {

    @Id
    @GeneratedValue
    @Column(name="guest_id")
    private String guestId;
    private String time;
    private String type;

}
