package com.example.demoSpringLoginMicroService.config;

import com.example.demoSpringLoginMicroService.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    public User parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            User user= new User();
            user.setEmailAddress(body.getSubject());
            user.setUserId(Integer.parseInt((String)body.get("userId")));
            user.setRole((String) body.get("role"));

            return user;

        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }

    public String generateToken(User user){
        Claims claims = Jwts.claims().setSubject(user.getEmailAddress());
        claims.put("userId", user.getUserId() + "");
        claims.put("role", user.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
