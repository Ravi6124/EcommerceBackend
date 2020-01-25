package com.example.demoSpringLoginMicroService.controller;

import com.example.demoSpringLoginMicroService.entity.Guest;
import com.example.demoSpringLoginMicroService.response.ApiResponse;
import com.example.demoSpringLoginMicroService.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/guest")
public class GuestController {

    @Autowired
    GuestService guestService;

    @PostMapping
    public ResponseEntity<ApiResponse> saveGuest(@RequestParam String type) {
        Guest guest = new Guest();
        guest.setType(type);
        Guest guestCreated = guestService.save(guest);
        String guestId=String.valueOf(guestCreated.getGuestId());
        if(guestCreated!=null)
            return new ResponseEntity<>(new ApiResponse(guestId),HttpStatus.OK);
        return new ResponseEntity<>(new ApiResponse(800, "Guest User not successfully registered"), HttpStatus.OK);
    }

}
