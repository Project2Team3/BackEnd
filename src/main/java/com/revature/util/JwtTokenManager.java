package com.revature.util;


import com.revature.exceptions.AuthenticationException;
import com.revature.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenManager {

    private final Key key;
    
    public JwtTokenManager(){
        key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String issueToken(User user){
        return Jwts.builder()
                .setId(String.valueOf(user.getId()))
                .setSubject(user.getUsername())
                .setIssuer("Trivia API")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(key).compact();
    }
}
