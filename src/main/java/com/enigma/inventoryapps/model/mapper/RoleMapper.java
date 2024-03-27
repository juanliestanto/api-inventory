package com.enigma.inventoryapps.model.mapper;

import com.enigma.inventoryapps.model.entity.Role;
import com.enigma.inventoryapps.model.request.AuthRequest;

public class RoleMapper {
    public static Role mapToEntity(AuthRequest authRequest){
        return Role.builder()
                .name(authRequest.getRole())
                .build();
    }
}
