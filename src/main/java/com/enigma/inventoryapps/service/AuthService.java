package com.enigma.inventoryapps.service;

import com.enigma.inventoryapps.model.request.AuthRequest;
import com.enigma.inventoryapps.model.response.LoginResponse;
import com.enigma.inventoryapps.model.response.RegisterResponse;

public interface AuthService {
    RegisterResponse register (AuthRequest authRequest);
    LoginResponse login(AuthRequest authRequest);
}
