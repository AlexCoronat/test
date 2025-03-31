package com.tecnical.test.service.impl;

import com.tecnical.test.controller.request.LoginRequest;
import com.tecnical.test.controller.response.AuthResponse;
import com.tecnical.test.dto.UserDTO;
import com.tecnical.test.model.Rol;
import com.tecnical.test.model.User;
import com.tecnical.test.repository.RolRepository;
import com.tecnical.test.repository.UserRepository;
import com.tecnical.test.service.AuthService;
import com.tecnical.test.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RolRepository rolRepository;


    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), request.getPassword()));
        UserDetails userDetail=userRepository.findByEmail(request.getEmail());
        String token=jwtService.getToken(userDetail,request.getEmail());
        return AuthResponse.builder()
                .token(token)
                .build();

    }

    public AuthResponse register(UserDTO userDTO) {
        Rol rol = rolRepository.findById(userDTO.getRol().getIdRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        User user = User.builder()
                .name(userDTO.getName())
                .password(passwordEncoder.encode( userDTO.getPassword()))
                .email(userDTO.getEmail())
                .rol(rol)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user,userDTO.getEmail()))
                .build();

    }
}
