package com.example.demoSpringLoginMicroService.controller;

import com.example.demoSpringLoginMicroService.ResponseLogin;
import com.example.demoSpringLoginMicroService.config.JwtUtil;
import com.example.demoSpringLoginMicroService.config.bcrypt.BCryptPasswordEncoder;
import com.example.demoSpringLoginMicroService.dto.LoginDTO;
import com.example.demoSpringLoginMicroService.dto.FacebookDTO;
import com.example.demoSpringLoginMicroService.dto.SwapCartDTO;
import com.example.demoSpringLoginMicroService.dto.UserDTO;
import com.example.demoSpringLoginMicroService.entity.User;
import com.example.demoSpringLoginMicroService.feignClients.AddToCartOrderClient;
import com.example.demoSpringLoginMicroService.response.ApiResponse;
import com.example.demoSpringLoginMicroService.service.GoogleService;
import com.example.demoSpringLoginMicroService.service.GuestService;
import com.example.demoSpringLoginMicroService.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    GoogleService googleService;

    @Autowired
    GuestService guestService;

    @Autowired
    AddToCartOrderClient addToCartOrderClient;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private
    JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<ApiResponse> userLogin(@RequestBody UserDTO userDTO) {

        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user = userService.findUser(user);

        if (null != user) {
            if (user.getPassword().equals(userDTO.getPassword())) {

                String accessToken = jwtUtil.generateToken(user);
                SwapCartDTO swapCartDTO=new SwapCartDTO();
                swapCartDTO.setUserId(String.valueOf(user.getUserId()));
                swapCartDTO.setGuestId(userDTO.getGuestId());
                addToCartOrderClient.swapCarts(swapCartDTO);
                //guestService.deleteById(userDTO.getGuestId());

                return new ResponseEntity<>(new ApiResponse(1000,accessToken,String.valueOf(user.getUserId()),user.getEmailAddress()), HttpStatus.OK);
            } else {

                return new ResponseEntity<>(new ApiResponse(800, "Password is not correct"), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new ApiResponse(800, "Invalid Credentials"), HttpStatus.OK);
    }

    @PostMapping("/googlelogin")
    public ResponseEntity<ApiResponse> gmailLogin(@RequestBody LoginDTO loginDTO) {
        System.out.println("Inside gmail login");
        User user = googleService.getGmailDetails(loginDTO);
        if (user != null)
        {
            SwapCartDTO swapCartDTO=new SwapCartDTO();
            swapCartDTO.setUserId(String.valueOf(user.getUserId()));
            swapCartDTO.setGuestId(loginDTO.getGuestId());
            addToCartOrderClient.swapCarts(swapCartDTO);
           // guestService.deleteById(loginDTO.getGuestId());
            return new ResponseEntity<>(new ApiResponse(1000,loginDTO.getAccessToken(),String.valueOf(user.getUserId()),user.getEmailAddress()), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(new ApiResponse(800, "User Not Found"), HttpStatus.OK);
    }

    @PostMapping("/facebooklogin")
    public ResponseEntity<ApiResponse> facebookLogin(@RequestBody LoginDTO loginDTO) {

        FacebookDTO userDTO = (new RestTemplate()).getForObject("https://graph.facebook.com/me?fields=name,id,email,first_name,last_name&access_token=" + loginDTO.getAccessToken(), FacebookDTO.class);
        User user = new User();
        if (userDTO != null) {
            System.out.println(userDTO.getEmail());

            user.setEmailAddress(userDTO.getEmail());
            user.setRole(loginDTO.getRole());
            boolean userExists = userService.checkEmailExists(user.getEmailAddress(), user.getRole());
            if (!userExists) {
                userService.save(user);
                SwapCartDTO swapCartDTO=new SwapCartDTO();
                swapCartDTO.setUserId(String.valueOf(user.getUserId()));
                swapCartDTO.setGuestId(loginDTO.getGuestId());
                addToCartOrderClient.swapCarts(swapCartDTO);
                //guestService.deleteById(loginDTO.getGuestId());
            }
            return new ResponseEntity<>(new ApiResponse(1000,loginDTO.getAccessToken(),String.valueOf(user.getUserId()),user.getEmailAddress()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(800, "User Not Found"), HttpStatus.OK);
    }
}