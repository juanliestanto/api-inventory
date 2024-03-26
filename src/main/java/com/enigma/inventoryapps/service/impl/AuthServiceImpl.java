package com.enigma.inventoryapps.service.impl;

import com.enigma.inventoryapps.constant.ERole;
import com.enigma.inventoryapps.model.entity.*;
import com.enigma.inventoryapps.model.request.AuthRequest;
import com.enigma.inventoryapps.model.response.LoginResponse;
import com.enigma.inventoryapps.model.response.RegisterResponse;
import com.enigma.inventoryapps.repository.AdminRepository;
import com.enigma.inventoryapps.repository.UserRepository;
import com.enigma.inventoryapps.security.JwtUtil;
import com.enigma.inventoryapps.service.AdminService;
import com.enigma.inventoryapps.service.AuthService;
import com.enigma.inventoryapps.service.RoleService;
import com.enigma.inventoryapps.service.StaffService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final AdminService adminService;
    private final StaffService staffService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    @Override
    @Transactional(rollbackOn = Exception.class)
    public RegisterResponse register(AuthRequest authRequest) {
        //TODO SET ROLE
        Role role = Role.builder()
                .name(authRequest.getRole())
                .build();
        role = roleService.getOrSave(role);

        //TODO SET USER CREDENTIALS
        User user = User.builder()
                .email(authRequest.getEmail())
                .password(passwordEncoder.encode(authRequest.getPassword()))
                .role(role)
                .build();

        userRepository.insertAndFlush(user);

        if(user.getRole().getName().equals(ERole.ROLE_ADMIN)){
            Admin admin =  Admin.builder()
                    .id(UUID.randomUUID().toString())
                    .name(authRequest.getName())
                    .phone(authRequest.getPhone())
                    .isActive(true)
                    .user(user)
                    .build();
            adminService.create(admin);
        }else if(user.getRole().getName().equals(ERole.ROLE_STAFF)){
            Staff staff = Staff.builder()
                    .id(UUID.randomUUID().toString())
                    .name(authRequest.getName())
                    .phone(authRequest.getPhone())
                    .division(authRequest.getDivision())
                    .isActive(true)
                    .user(user)
                    .build();
            staffService.create(staff);
        }

        return RegisterResponse.builder()
                .email(user.getEmail())
                .role(user.getRole().getName())
                .build();
    }


    @Override
    public LoginResponse login(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser appUser = (AppUser) authentication.getPrincipal();
        String token = jwtUtil.generateToken(appUser);

        return LoginResponse.builder()
                .token(token)
                .email(appUser.getUsername())
                .role(appUser.getRole().name())
                .build();
    }
}

