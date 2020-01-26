package com.example.demoSpringLoginMicroService.service.impl;

import com.example.demoSpringLoginMicroService.dto.LoginDTO;
import com.example.demoSpringLoginMicroService.dto.UserDTO;
import com.example.demoSpringLoginMicroService.entity.LoginHistory;
import com.example.demoSpringLoginMicroService.repository.LoginHistoryRepository;
import com.example.demoSpringLoginMicroService.service.LoginHistoryService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class LoginHistoryServiceImpl implements LoginHistoryService {

    @Autowired
    LoginHistoryRepository loginHistoryRepository;

    @Override
    public LoginHistory save(LoginHistory loginHistory) {
        return loginHistoryRepository.save(loginHistory);
    }

    @Override
    public List<LoginHistory> findLoginHistory(int userId) {
        return loginHistoryRepository.findLoginHistory(userId);
    }

    @Override
    public LoginHistory setLoginHistory(UserDTO userDTO) {
        LoginHistory loginHistory=new LoginHistory();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        loginHistory.setLoginTime(timestamp.toString());
        loginHistory.setLoginSource(userDTO.getLoginSource());
        loginHistory.setType(userDTO.getType());
        return loginHistory;
    }

    @Override
    public LoginHistory setLoginHistory(LoginDTO loginDTO) {
        LoginHistory loginHistory=new LoginHistory();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        loginHistory.setLoginTime(timestamp.toString());
        loginHistory.setLoginSource(loginDTO.getLoginSource());
        loginHistory.setType(loginDTO.getType());
        return loginHistory;
    }

}
