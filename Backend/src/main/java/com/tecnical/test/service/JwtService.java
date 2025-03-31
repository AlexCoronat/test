package com.tecnical.test.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    String getToken(UserDetails userDetails, String email);
    String getToken(Map<String, Object> extraClaims, UserDetails userDetails, String email);
    Key getKey();
    String getUsernameFromToken(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
    <T> T getClaim(String token, Function<Claims,T> claimsResolver);

}
