package com.example.selfintro.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.selfintro.dto.RegisterRequest;
import com.example.selfintro.entity.User;
import com.example.selfintro.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                .password(user.getPassword()) // Plain text for simplicity per previous context, usually BCrypt
                .roles("USER")
                .build();
    }

    public void registerUser(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }

        // Create User entity from DTO
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword()); // In a real app, encode password here
        user.setSelfIntroduction(null); // Will be filled later by user

        userRepository.save(user);
    }
}
