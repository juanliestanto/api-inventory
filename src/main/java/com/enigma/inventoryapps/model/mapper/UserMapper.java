package com.enigma.inventoryapps.model.mapper;

import com.enigma.inventoryapps.model.entity.User;
import com.enigma.inventoryapps.model.response.UserResponse;

public class UserMapper {
    public static UserResponse mapToResponse(User user){
        return UserResponse.builder()
                .email(user.getEmail())
                .role(user.getRole().getName())
                .build();
    }
}
