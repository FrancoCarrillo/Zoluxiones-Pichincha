package com.zoluxiones.pichincha.security.infraestructure.services;

import com.zoluxiones.pichincha.security.core.entities.Role;
import com.zoluxiones.pichincha.security.core.repositories.RolRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class RoleService {
    private RolRepository rolRepository;

    private static final String[] DEFAULT_ROLES = {"USER", "ADMIN"};

    public void seedRol() {
        Arrays.stream(DEFAULT_ROLES).forEach(name -> {
            if(!rolRepository.existsByName(name)) {
                rolRepository.save(new Role().withName(name));
            }
        } );

    }
}
