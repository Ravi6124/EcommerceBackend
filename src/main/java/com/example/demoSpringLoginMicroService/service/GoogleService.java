package com.example.demoSpringLoginMicroService.service;

import com.example.demoSpringLoginMicroService.entity.User;

import java.util.Optional;

public interface GoogleService {
    User getGmailDetails(String accessToken);
}
