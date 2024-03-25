package com.enigma.inventoryapps.service.impl;

import com.enigma.inventoryapps.model.entity.Admin;
import com.enigma.inventoryapps.model.mapper.AdminMapper;
import com.enigma.inventoryapps.model.request.AdminRequest;
import com.enigma.inventoryapps.model.response.AdminResponse;
import com.enigma.inventoryapps.repository.AdminRepository;
import com.enigma.inventoryapps.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public Admin create(Admin admin) {
        return adminRepository.insert(admin);
    }

    @Override
    public AdminResponse getById(String id) {
        Admin admin = this.getEntityById(id);
        return AdminMapper.mapToResponse(admin);
    }

    @Override
    public Admin getEntityById(String id) {
        return adminRepository.findById(id).orElseThrow(() -> new ResponseStatusException((HttpStatus.NOT_FOUND), "Admin Not Found"));
    }

    @Override
    public List<AdminResponse> getAll() {
        List<Admin> adminList = adminRepository.findAll();
        List<AdminResponse> responses = adminList.stream()
                .map(AdminMapper::mapToResponse).toList();
        return responses;
    }

    @Override
    public AdminResponse update(AdminRequest adminRequest) {
        Admin adminId = this.getEntityById(adminRequest.getId());

        Admin admin = Admin.builder()
                .id(adminId.getId())
                .name(adminRequest.getName())
                .phone(adminId.getPhone())
                .isActive(true)
                .build();
        adminRepository.update(admin);
        return AdminMapper.mapToResponse(admin);
    }

    @Override
    public void delete(String id) {
        Admin admin = this.getEntityById(id);
        adminRepository.delete(admin.getId());
    }
}

