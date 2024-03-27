package com.enigma.inventoryapps.model.mapper;

import com.enigma.inventoryapps.model.entity.AppUser;
import com.enigma.inventoryapps.model.entity.User;

public class AppUserMapper {

    public static AppUser mapToEntity(User user){
        return AppUser.builder()
                .id(user.getId())
                .username(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole().getName())
                .build();
    }
}
