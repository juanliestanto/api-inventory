package com.enigma.inventoryapps.service;

import com.enigma.inventoryapps.model.entity.Admin;
import com.enigma.inventoryapps.model.request.AdminRequest;
import com.enigma.inventoryapps.model.response.AdminResponse;

import java.util.List;

public interface AdminService {
    Admin create (Admin admin);
    AdminResponse getById(String id);
    Admin getEntityById(String id);
    List<AdminResponse> getAll();
    AdminResponse update (AdminRequest adminRequest);
    void delete(String id);
}

