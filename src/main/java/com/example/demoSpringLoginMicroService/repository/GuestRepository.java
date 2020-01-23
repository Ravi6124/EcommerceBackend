package com.example.demoSpringLoginMicroService.repository;

import com.example.demoSpringLoginMicroService.entity.Guest;
import org.springframework.data.repository.CrudRepository;

public interface GuestRepository extends CrudRepository<Guest,Integer> {
}
