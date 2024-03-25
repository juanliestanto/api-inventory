package com.enigma.inventoryapps.controller;

import com.enigma.inventoryapps.constant.AppPath;
import com.enigma.inventoryapps.model.response.CommonResponse;
import com.enigma.inventoryapps.model.response.UserResponse;
import com.enigma.inventoryapps.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.USER)
public class UserController {

    private final UserService userService;

    @GetMapping(AppPath.GET_BY_ID)
    public ResponseEntity<?> findUser (@PathVariable String id){

        UserResponse userResponse = userService.findUserById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<UserResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get user")
                        .data(userResponse)
                        .build());
    }
}
