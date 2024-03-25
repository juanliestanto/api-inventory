package com.enigma.inventoryapps.service.impl;

import com.enigma.inventoryapps.model.entity.AppUser;
import com.enigma.inventoryapps.model.entity.User;
import com.enigma.inventoryapps.model.response.UserResponse;
import com.enigma.inventoryapps.repository.UserRepository;
import com.enigma.inventoryapps.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public AppUser loadUserById(String id) {
        User userCredential = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Invalid Credential"));

        return AppUser.builder()
                .id(userCredential.getId())
                .username(userCredential.getEmail())
                .password(userCredential.getPassword())
                .role(userCredential.getRole().getName())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userCredential = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Invalid Credential"));

        return AppUser.builder()
                .id(userCredential.getId())
                .username(userCredential.getEmail())
                .password(userCredential.getPassword())
                .role(userCredential.getRole().getName())
                .build();
    }

    @Override
    public UserResponse findUserById(String id) {
        User userCredential = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Invalid Credential"));

        return UserResponse.builder()
                .email(userCredential.getEmail())
                .role(userCredential.getRole().getName())
                .build();
    }
}

