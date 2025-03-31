package com.tecnical.test.controller;

import com.tecnical.test.controller.request.LoginRequest;
import com.tecnical.test.controller.response.AuthResponse;
import com.tecnical.test.dto.UserDTO;
import com.tecnical.test.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(loginRequest));
    }
    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserDTO userDTO)
    {
        return ResponseEntity.ok(authService.register(userDTO));
    }
}
