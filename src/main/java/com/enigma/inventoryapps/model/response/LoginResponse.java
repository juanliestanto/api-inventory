package com.enigma.inventoryapps.model.response;

import com.enigma.inventoryapps.constant.ERole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class LoginResponse {
    private String email;
    private String role;
    private String token;
}
