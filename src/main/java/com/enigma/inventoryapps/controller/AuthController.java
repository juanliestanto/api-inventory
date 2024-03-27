package com.enigma.inventoryapps.controller;

import com.enigma.inventoryapps.constant.AppPath;
import com.enigma.inventoryapps.model.request.AuthRequest;
import com.enigma.inventoryapps.model.response.CommonResponse;
import com.enigma.inventoryapps.model.response.LoginResponse;
import com.enigma.inventoryapps.model.response.RegisterResponse;
import com.enigma.inventoryapps.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.AUTH)
public class AuthController {

    private final AuthService authService;

    @PostMapping(AppPath.SIGNUP)
    public ResponseEntity<?> signup (@RequestBody AuthRequest authRequest){

        RegisterResponse registerResponse = authService.register(authRequest);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<RegisterResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Sucessfully Register")
                        .data(registerResponse)
                        .build());
    }

    @PostMapping(AppPath.SIGNIN)
    public ResponseEntity<?> signin (@RequestBody AuthRequest authRequest){

        LoginResponse registerResponse = authService.login(authRequest);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<LoginResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Sucessfully Login")
                        .data(registerResponse)
                        .build());
    }
}

