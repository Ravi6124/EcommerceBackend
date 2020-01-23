package com.example.demoSpringLoginMicroService.service;

import com.example.demoSpringLoginMicroService.entity.Guest;

import java.util.Optional;

public interface GuestService {
    Guest save(Guest guest);
}
