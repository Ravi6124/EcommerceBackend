package com.example.DemoLoginMicroService.repository;

import com.example.DemoLoginMicroService.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
}
