package com.example.demoSpringLoginMicroService.service.impl;

import com.example.demoSpringLoginMicroService.dto.LoginDTO;
import com.example.demoSpringLoginMicroService.entity.User;
import com.example.demoSpringLoginMicroService.repository.UserRepository;
import com.example.demoSpringLoginMicroService.service.GoogleService;
import com.example.demoSpringLoginMicroService.service.UserService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;

@Service
public class GoogleServiceImpl implements GoogleService {


    @Autowired
    UserService userService;

    private static final String GOOGLE_APP_CLIENT_ID = "82806335202-ij3bqro9rstgi5ad5el56n5tmrovlaiv.apps.googleusercontent.com";
    @Value(GOOGLE_APP_CLIENT_ID)
    private List<String> googleAppClientIdList;
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private HttpTransport httpTransport;

    @PostConstruct
    public void init() throws GeneralSecurityException, IOException {
        httpTransport = GoogleNetHttpTransport.newTrustedTransport();
    }

    public GoogleIdTokenVerifier getGoogleIdTokenVerifier() {
        return new GoogleIdTokenVerifier.Builder
                (httpTransport, JSON_FACTORY).setAudience(googleAppClientIdList).build();
    }


    @Override
    public User getGmailDetails(LoginDTO loginDTO) {
        //System.out.println("Inside Gmail Details");
        User user = new User();
        try {

            GoogleIdToken verifyGoogleIdToken = getGoogleIdTokenVerifier().verify(loginDTO.getAccessToken());
            if (verifyGoogleIdToken != null) {
                // System.out.println("Inside verifyGoogleIDToken");
                user.setEmailAddress(verifyGoogleIdToken.getPayload().getEmail());
                user.setRole(loginDTO.getRole());
                //System.out.println(verifyGoogleIdToken.getPayload().getEmail();
                boolean userExists=userService.checkEmailExists(user);
                if (!userExists)
                    userService.save(user);
            } else {
                System.out.println("Not valid Token");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
