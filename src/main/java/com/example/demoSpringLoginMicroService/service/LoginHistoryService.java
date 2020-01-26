package com.example.demoSpringLoginMicroService.service;

import com.example.demoSpringLoginMicroService.dto.LoginDTO;
import com.example.demoSpringLoginMicroService.dto.UserDTO;
import com.example.demoSpringLoginMicroService.entity.LoginHistory;
import com.example.demoSpringLoginMicroService.entity.User;

import java.util.List;

public interface LoginHistoryService {

    public LoginHistory save(LoginHistory loginHistory);
    List<LoginHistory> findLoginHistory(int userId);

    LoginHistory setLoginHistory(UserDTO userDTO);

    LoginHistory setLoginHistory(LoginDTO loginDTO);
}
