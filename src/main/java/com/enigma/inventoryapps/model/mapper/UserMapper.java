package com.enigma.inventoryapps.model.mapper;

import com.enigma.inventoryapps.model.entity.Role;
import com.enigma.inventoryapps.model.entity.User;
import com.enigma.inventoryapps.model.request.AuthRequest;
import com.enigma.inventoryapps.model.response.UserResponse;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserMapper {
    public static UserResponse mapToResponse(User user){
        return UserResponse.builder()
                .email(user.getEmail())
                .role(user.getRole().getName())
                .build();
    }

    public static User mapToEntity(AuthRequest authRequest, Role role, PasswordEncoder passwordEncoder){
       return User.builder()
                .email(authRequest.getEmail())
                .password(passwordEncoder.encode(authRequest.getPassword()))
                .role(role)
                .build();
    }
}
