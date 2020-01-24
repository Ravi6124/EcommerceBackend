package com.example.demoSpringLoginMicroService.repository;

import com.example.demoSpringLoginMicroService.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    Optional<User> findByEmailAddress(String emailAddress);
    Optional<User> findByRole(String role);

    Optional<User> findByEmailAddressAndRole(String emailAddress, String role);
}
