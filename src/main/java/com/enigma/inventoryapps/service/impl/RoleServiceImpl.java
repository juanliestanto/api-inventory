package com.enigma.inventoryapps.service.impl;

import com.enigma.inventoryapps.model.entity.Role;
import com.enigma.inventoryapps.repository.RoleRepository;
import com.enigma.inventoryapps.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getOrSave(Role role) {
        Optional<Role> optionalRole = roleRepository.getRoleName(role.getName().name());

        if(optionalRole.isPresent()){
            return optionalRole.get();
        }

        roleRepository.insertAndFlush(role);

        return role;
    }
}
