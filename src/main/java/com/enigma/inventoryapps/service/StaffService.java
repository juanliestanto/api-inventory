package com.enigma.inventoryapps.service;

import com.enigma.inventoryapps.model.entity.Staff;
import com.enigma.inventoryapps.model.request.StaffRequest;
import com.enigma.inventoryapps.model.response.StaffResponse;

import java.util.List;

public interface StaffService {
    Staff create (Staff staff);
    StaffResponse getById(String id);
    Staff getEntityById(String id);
    List<StaffResponse> getAll();
    StaffResponse update (StaffRequest staffRequest);
    void delete(String id);
}
