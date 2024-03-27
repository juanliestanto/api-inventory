package com.enigma.inventoryapps.model.mapper;

import com.enigma.inventoryapps.model.entity.Staff;
import com.enigma.inventoryapps.model.entity.User;
import com.enigma.inventoryapps.model.request.AuthRequest;
import com.enigma.inventoryapps.model.request.StaffRequest;
import com.enigma.inventoryapps.model.response.StaffResponse;

import java.util.UUID;

public class StaffMapper {
    public static StaffResponse mapToResponse(Staff staff){
        return StaffResponse.builder()
                .id(staff.getId())
                .name(staff.getPhone())
                .phone(staff.getPhone())
                .division(staff.getDivision().name())
                .build();
    }

    public static Staff mapToEntity(AuthRequest authRequest, User user){
        return Staff.builder()
                .id(UUID.randomUUID().toString())
                .name(authRequest.getName())
                .phone(authRequest.getPhone())
                .division(authRequest.getDivision())
                .isActive(true)
                .user(user)
                .build();
    }
}
