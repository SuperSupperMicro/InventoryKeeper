package com.heftyb.inventorykeeper.repositories;

import com.heftyb.inventorykeeper.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRoleIgnoreCase(String name);
}
