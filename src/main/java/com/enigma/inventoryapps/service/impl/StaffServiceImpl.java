package com.enigma.inventoryapps.service.impl;

import com.enigma.inventoryapps.model.entity.Staff;
import com.enigma.inventoryapps.model.mapper.StaffMapper;
import com.enigma.inventoryapps.model.request.StaffRequest;
import com.enigma.inventoryapps.model.response.StaffResponse;
import com.enigma.inventoryapps.repository.StaffRepository;
import com.enigma.inventoryapps.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    @Override
    public Staff create(Staff staff) {
        return staffRepository.insert(staff);
    }

    @Override
    public StaffResponse getById(String id) {
        Staff staff = this.getEntityById(id);
        return StaffMapper.mapToResponse(staff);
    }

    @Override
    public Staff getEntityById(String id) {
        return staffRepository.findStaff(id).orElseThrow(() -> new ResponseStatusException((HttpStatus.NOT_FOUND), "Staff Not Found"));
    }

    @Override
    public List<StaffResponse> getAll() {
        List<Staff> staffList = staffRepository.findAllStaff();
        List<StaffResponse> responses = staffList.stream()
                .map(StaffMapper::mapToResponse).toList();
        return responses;
    }

    @Override
    public StaffResponse update(StaffRequest staffRequest) {
        Staff staffId = this.getEntityById(staffRequest.getId());

        Staff staff = Staff.builder()
                .id(staffId.getId())
                .name(staffRequest.getName())
                .phone(staffRequest.getPhone())
                .division(staffRequest.getDivision())
                .isActive(true)
                .build();
        staffRepository.update(staff);
        return StaffMapper.mapToResponse(staff);
    }

    @Override
    public void delete(String id) {
        Staff staffId = this.getEntityById(id);
        staffRepository.delete(staffId.getId());
    }
}

