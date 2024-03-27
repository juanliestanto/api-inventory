package com.enigma.inventoryapps.model.mapper;

import com.enigma.inventoryapps.model.entity.Admin;
import com.enigma.inventoryapps.model.entity.User;
import com.enigma.inventoryapps.model.request.AdminRequest;
import com.enigma.inventoryapps.model.request.AuthRequest;
import com.enigma.inventoryapps.model.response.AdminResponse;

import java.util.UUID;

public class AdminMapper {

    public static AdminResponse mapToResponse(Admin admin){
        return AdminResponse.builder()
                .id(admin.getId())
                .name(admin.getName())
                .phone(admin.getPhone())
                .build();
    }

    public static Admin mapToEntity(AuthRequest authRequest, User user){
        return Admin.builder()
                .id(UUID.randomUUID().toString())
                .name(authRequest.getName())
                .phone(authRequest.getPhone())
                .isActive(true)
                .user(user)
                .build();
    }
}
