package com.enigma.inventoryapps.service.impl;

import com.enigma.inventoryapps.constant.ERole;
import com.enigma.inventoryapps.model.entity.*;
import com.enigma.inventoryapps.model.mapper.AdminMapper;
import com.enigma.inventoryapps.model.mapper.RoleMapper;
import com.enigma.inventoryapps.model.mapper.StaffMapper;
import com.enigma.inventoryapps.model.mapper.UserMapper;
import com.enigma.inventoryapps.model.request.AuthRequest;
import com.enigma.inventoryapps.model.response.LoginResponse;
import com.enigma.inventoryapps.model.response.RegisterResponse;
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
        Role role = RoleMapper.mapToEntity(authRequest);
        role = roleService.getOrSave(role);

        //TODO SET USER CREDENTIALS
        User user = UserMapper.mapToEntity(authRequest, role, passwordEncoder);

        userRepository.insertAndFlush(user);

        //TODO SET ENTITY (ADMIN/STAFF)
        if(user.getRole().getName().equals(ERole.ROLE_ADMIN)){
            Admin admin = AdminMapper.mapToEntity(authRequest, user);
            adminService.create(admin);
        }else if(user.getRole().getName().equals(ERole.ROLE_STAFF)){
            Staff staff = StaffMapper.mapToEntity(authRequest, user);
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

