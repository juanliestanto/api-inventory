package com.enigma.inventoryapps.service;

import com.enigma.inventoryapps.model.entity.Role;

public interface RoleService {
    Role getOrSave(Role role);
}
