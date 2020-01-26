package com.example.demoSpringLoginMicroService.controller;

import com.example.demoSpringLoginMicroService.dto.CustomerDto;
import com.example.demoSpringLoginMicroService.dto.MerchantDTO;
import com.example.demoSpringLoginMicroService.dto.UserDTO;
import com.example.demoSpringLoginMicroService.entity.User;
import com.example.demoSpringLoginMicroService.feignClients.CustomerClient;
import com.example.demoSpringLoginMicroService.feignClients.MerchantClient;
import com.example.demoSpringLoginMicroService.response.ApiResponse;
import com.example.demoSpringLoginMicroService.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/customer")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CustomerClient customerClient;

    @Autowired
    MerchantClient merchantClient;

    @PostMapping
    public ResponseEntity<ApiResponse> saveUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        CustomerDto customerDto=new CustomerDto();
        MerchantDTO merchantDTO=new MerchantDTO();
        BeanUtils.copyProperties(userDTO, user);
        User emailExists = userService.checkEmailExists(userDTO.getEmailAddress(), userDTO.getRole());
        //System.out.println("status :" + emailExists);
        if (emailExists!=null) {
            return new ResponseEntity<>(new ApiResponse(800,"User already exists"), HttpStatus.OK);
        } else {
            User userCreated = userService.save(user);
            if(userCreated.getRole().equals("customer"))
            {
                customerDto.setCustomerId(String.valueOf(userCreated.getUserId()));
                customerDto.setEmail(userCreated.getEmailAddress());
                customerClient.addCustomer(customerDto);
            }
            else
            {
                merchantDTO.setMerchantId(String.valueOf(userCreated.getUserId()));
                merchantDTO.setEmail(userCreated.getEmailAddress());
                merchantClient.addMerchant(merchantDTO);
            }

            return new ResponseEntity<>(new ApiResponse(1000,"User successfully registered"), HttpStatus.OK);

        }

    }
}
