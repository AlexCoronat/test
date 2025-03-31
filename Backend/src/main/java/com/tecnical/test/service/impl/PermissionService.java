package com.tecnical.test.service.impl;

import com.tecnical.test.model.Permission;
import com.tecnical.test.model.User;
import com.tecnical.test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class PermissionService {
    private final UserService userService;
    public List<String> getKeyPermissions() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getPrincipal() == null || !(auth.getPrincipal() instanceof UserDetails)) {
            return Collections.emptyList();
        }
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        User user = userService.getByName(userDetails.getUsername());
        if (user == null || user.getRol() == null) {
            return Collections.emptyList();
        }
        return user.getRol().getPermissions()
                .stream()
                .map(Permission::getKeyName)
                .collect(Collectors.toList());
    }
    public User getUserDetails(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getPrincipal() == null || !(auth.getPrincipal() instanceof UserDetails)) {
            return null;
        }
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return userService.getByName(userDetails.getUsername());
    }
}
