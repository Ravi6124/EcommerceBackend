package com.example.demoSpringLoginMicroService.service.impl;

import com.example.demoSpringLoginMicroService.entity.Guest;
import com.example.demoSpringLoginMicroService.repository.GuestRepository;
import com.example.demoSpringLoginMicroService.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    GuestRepository guestRepository;

    @Override
    public Guest save(Guest guest) {
        Guest guestCreate=new Guest();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        guestCreate.setTime(timestamp.toString());
        guestCreate.setType(guest.getType());
        guestRepository.save(guestCreate);
        return guestCreate;
    }
}
