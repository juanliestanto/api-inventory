package com.enigma.inventoryapps.model.mapper;

import com.enigma.inventoryapps.model.entity.Admin;
import com.enigma.inventoryapps.model.request.AdminRequest;
import com.enigma.inventoryapps.model.response.AdminResponse;

public class AdminMapper {

    public static AdminResponse mapToResponse(Admin admin){
        return AdminResponse.builder()
                .id(admin.getId())
                .name(admin.getName())
                .phone(admin.getPhone())
                .build();
    }

    public static Admin mapToEntity(AdminRequest adminRequest){
        return Admin.builder()
                .name(adminRequest.getName())
                .phone(adminRequest.getPhone())
                .isActive(true)
                .build();
    }
}
