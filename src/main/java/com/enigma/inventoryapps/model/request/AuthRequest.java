package com.enigma.inventoryapps.model.request;

import com.enigma.inventoryapps.constant.EDivision;
import com.enigma.inventoryapps.constant.ERole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class AuthRequest {
    private String email;
    private String password;
    private ERole role;
    private String name;
    private String phone;
    private EDivision division;
}
