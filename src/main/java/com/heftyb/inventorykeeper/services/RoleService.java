package com.heftyb.inventorykeeper.services;

import com.heftyb.inventorykeeper.models.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    Role findById(long id);
    Role findByName(String name);
}
