package com.enigma.inventoryapps.service;

import com.enigma.inventoryapps.model.entity.AppUser;
import com.enigma.inventoryapps.model.response.UserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


public interface UserService extends UserDetailsService {
    AppUser loadUserById(String id);

    UserResponse findUserById(String id);
}