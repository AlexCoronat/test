package com.tecnical.test.service.impl;

import com.tecnical.test.model.User;
import com.tecnical.test.repository.UserRepository;
import com.tecnical.test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public User getByName(String name) {
        return userRepository.findByName(name);
    }
}
