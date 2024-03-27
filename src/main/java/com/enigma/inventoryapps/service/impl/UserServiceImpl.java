package com.enigma.inventoryapps.service.impl;

import com.enigma.inventoryapps.model.entity.AppUser;
import com.enigma.inventoryapps.model.entity.User;
import com.enigma.inventoryapps.model.mapper.AppUserMapper;
import com.enigma.inventoryapps.model.mapper.UserMapper;
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
    //For verification jwt
    public AppUser loadUserById(String id) {
        User userCredential = userRepository.findUserId(id).orElseThrow(() -> new UsernameNotFoundException("Invalid Credential"));

        return AppUserMapper.mapToEntity(userCredential);
    }

    @Override
    //For verification username (email)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userCredential = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Invalid Credential"));

        return AppUserMapper.mapToEntity(userCredential);
    }

    @Override
    public UserResponse findUserById(String id) {
        User userCredential = userRepository.findUserId(id).orElseThrow(() -> new UsernameNotFoundException("Invalid Credential"));

        return UserMapper.mapToResponse(userCredential);
    }
}

