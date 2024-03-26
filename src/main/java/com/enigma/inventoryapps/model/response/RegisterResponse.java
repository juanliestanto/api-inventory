package com.enigma.inventoryapps.model.response;

import com.enigma.inventoryapps.constant.EDivision;
import com.enigma.inventoryapps.constant.ERole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class RegisterResponse {
    private String email;
    private ERole role;
}
