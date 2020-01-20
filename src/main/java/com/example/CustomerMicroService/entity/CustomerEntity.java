package com.example.CustomerMicroService.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@Table(name="Customer")
public class CustomerEntity {
    @Id
    String customerId;
    String email;
    String firstName;
    String lastName;
    String contactNumber;
    String address;
}
