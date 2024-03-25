package com.enigma.inventoryapps.model.mapper;

import com.enigma.inventoryapps.model.entity.Staff;
import com.enigma.inventoryapps.model.request.StaffRequest;
import com.enigma.inventoryapps.model.response.StaffResponse;

public class StaffMapper {
    public static StaffResponse mapToResponse(Staff staff){
        return StaffResponse.builder()
                .id(staff.getId())
                .name(staff.getPhone())
                .phone(staff.getPhone())
                .division(staff.getDivision().name())
                .build();
    }

    public static Staff mapToEntity(StaffRequest staffRequest){
        return Staff.builder()
                .name(staffRequest.getName())
                .phone(staffRequest.getPhone())
                .division(staffRequest.getDivision())
                .build();
    }
}
