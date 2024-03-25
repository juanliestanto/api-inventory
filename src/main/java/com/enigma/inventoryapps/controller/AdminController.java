package com.enigma.inventoryapps.controller;

import com.enigma.inventoryapps.constant.AppPath;
import com.enigma.inventoryapps.model.request.AdminRequest;
import com.enigma.inventoryapps.model.response.AdminResponse;
import com.enigma.inventoryapps.model.response.CommonResponse;
import com.enigma.inventoryapps.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.ADMIN)
public class AdminController {

    private final AdminService adminService;

    @GetMapping(AppPath.GET_BY_ID)
    ResponseEntity<?> getStaffById(@PathVariable String id){
        AdminResponse adminResponse = adminService.getById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<AdminResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get admin by id")
                        .data(adminResponse)
                        .build());
    }

    @GetMapping
    ResponseEntity<?> getAllStaff(){
        List<AdminResponse> adminResponses = adminService.getAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<List<AdminResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get all admin")
                        .data(adminResponses)
                        .build());
    }

    @PutMapping
    ResponseEntity<?> updateStaff(@RequestBody AdminRequest adminRequest){
        AdminResponse adminResponse = adminService.update(adminRequest);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<AdminResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully updated admin")
                        .data(adminResponse)
                        .build());
    }

    @DeleteMapping(AppPath.GET_BY_ID)
    ResponseEntity<?> deleteStaffById(@PathVariable String id){
        adminService.delete(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully delete admin by id")
                        .build());
    }
}

