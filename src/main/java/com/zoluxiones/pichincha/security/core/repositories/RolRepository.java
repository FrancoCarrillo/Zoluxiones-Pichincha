package com.zoluxiones.pichincha.security.core.repositories;

import com.zoluxiones.pichincha.security.core.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
    boolean existsByName(String name);
}
