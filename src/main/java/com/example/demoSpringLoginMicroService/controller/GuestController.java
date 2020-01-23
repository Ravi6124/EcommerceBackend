package com.example.demoSpringLoginMicroService.controller;

import com.example.demoSpringLoginMicroService.dto.GuestDTO;
import com.example.demoSpringLoginMicroService.dto.UserDTO;
import com.example.demoSpringLoginMicroService.entity.Guest;
import com.example.demoSpringLoginMicroService.entity.User;
import com.example.demoSpringLoginMicroService.response.ApiResponse;
import com.example.demoSpringLoginMicroService.service.GuestService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/guest")
public class GuestController {

    @Autowired
    GuestService guestService;

    @PostMapping
    public ResponseEntity<ApiResponse<String>> saveGuest(@RequestBody GuestDTO guestDTO) {
        Guest guest = new Guest();
        BeanUtils.copyProperties(guestDTO, guest);
        Guest guestCreated = guestService.save(guest);
        if(guestCreated!=null)
            return new ResponseEntity<>(new ApiResponse<>(1000, "Guest User successfully registered"), HttpStatus.OK);
        return new ResponseEntity<>(new ApiResponse<>(900, "Guest User not successfully registered"), HttpStatus.OK);
    }
}
