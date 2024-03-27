package com.enigma.inventoryapps.controller;

import com.enigma.inventoryapps.constant.AppPath;
import com.enigma.inventoryapps.model.request.AdminRequest;
import com.enigma.inventoryapps.model.response.AdminResponse;
import com.enigma.inventoryapps.model.response.CommonResponse;
import com.enigma.inventoryapps.service.AdminService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.ADMIN)
@SecurityRequirement(name = "Bearer configuration")
public class AdminController {

    private final AdminService adminService;

    @GetMapping(AppPath.GET_BY_ID)
    ResponseEntity<?> getAdminById(@PathVariable String id){
        AdminResponse adminResponse = adminService.getById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<AdminResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Get Admin By Id")
                        .data(adminResponse)
                        .build());
    }

    @GetMapping
    ResponseEntity<?> getAllAdmin(){
        List<AdminResponse> adminResponses = adminService.getAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<List<AdminResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Get All Admin")
                        .data(adminResponses)
                        .build());
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    ResponseEntity<?> updateAdmin(@RequestBody AdminRequest adminRequest){
        AdminResponse adminResponse = adminService.update(adminRequest);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<AdminResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Updated Admin")
                        .data(adminResponse)
                        .build());
    }

    @DeleteMapping(AppPath.GET_BY_ID)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    ResponseEntity<?> deleteAdminById(@PathVariable String id){
        adminService.delete(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Delete Admin By Id")
                        .build());
    }
}

