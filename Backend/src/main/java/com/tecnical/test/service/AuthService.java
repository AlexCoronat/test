package com.tecnical.test.service;

import com.tecnical.test.controller.request.LoginRequest;
import com.tecnical.test.controller.request.RegisterRequest;
import com.tecnical.test.controller.response.AuthResponse;
import com.tecnical.test.dto.UserDTO;

public interface AuthService {
    AuthResponse login(LoginRequest loginRequest);
    AuthResponse register(UserDTO userDTO);
}
