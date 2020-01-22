package com.example.demoSpringLoginMicroService.controller;

import com.example.demoSpringLoginMicroService.config.JwtUtil;
import com.example.demoSpringLoginMicroService.dto.AccessTokenDTO;
import com.example.demoSpringLoginMicroService.dto.FacebookDTO;
import com.example.demoSpringLoginMicroService.dto.UserDTO;
import com.example.demoSpringLoginMicroService.entity.User;
import com.example.demoSpringLoginMicroService.response.ApiResponse;
import com.example.demoSpringLoginMicroService.service.GoogleService;
import com.example.demoSpringLoginMicroService.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    GoogleService googleService;

    @Autowired
    private
    JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<ApiResponse<String>> userLogin(@RequestBody UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user = userService.findUser(user);
        if (null != user) {
            String accessToken = jwtUtil.generateToken(user);
            return new ResponseEntity<>(new ApiResponse<>(accessToken), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse<>(900, "Invalid Credentials"), HttpStatus.OK);
    }

    @PostMapping("/googlelogin")
    public ResponseEntity<ApiResponse<String>> gmailLogin(@RequestBody AccessTokenDTO accessTokenDTO) {
        System.out.println("Inside gmail login");
            User user=googleService.getGmailDetails(accessTokenDTO.getAccessToken());
            if(user!=null)
                return new ResponseEntity<>(new ApiResponse<>(accessTokenDTO.getAccessToken()), HttpStatus.OK);
             else
                return new ResponseEntity<>(new ApiResponse<>(900,"User Not Found"),HttpStatus.OK);
    }

    @PostMapping("/facebooklogin")
    public ResponseEntity<ApiResponse<String>> facebookLogin(@RequestBody AccessTokenDTO accessTokenDTO) {

        FacebookDTO userDTO=(new RestTemplate()).getForObject("https://graph.facebook.com/me?fields=name,id,email,first_name,last_name&access_token=" + accessTokenDTO.getAccessToken(), FacebookDTO.class);
        if(userDTO!=null){
            System.out.println(userDTO.getEmail());
            User user=new User();
            user.setEmailAddress(userDTO.getEmail());
            userService.save(user);
            return new ResponseEntity<>(new ApiResponse<>(900,"User Found"),HttpStatus.OK);
        }

        else
            return new ResponseEntity<>(new ApiResponse<>(900,"User Not Found"),HttpStatus.OK);
    }
}
