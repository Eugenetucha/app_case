package com.test_case.app.service;

import com.test_case.app.model.entity.User;
import com.test_case.app.model.entity.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.userRepository.findByUsername(username);

        if (user == null) {
            throw new RuntimeException("Not found: " + username);
        }
        return new UserDetails(user);
    }
}