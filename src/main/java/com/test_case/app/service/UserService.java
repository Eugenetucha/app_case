package com.test_case.app.service;

import com.test_case.app.model.entity.User;
import com.test_case.app.model.entity.UserDetails;
import com.test_case.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user != null){
            return new UserDetails(user);
        }else{
            return null;
        }
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
