package com.example.demoSpringLoginMicroService.service;

import com.example.demoSpringLoginMicroService.entity.Guest;

public interface GuestService {
    Guest save(Guest guest);
    //void deleteById(String guestId);
}
