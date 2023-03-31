package com.heftyb.inventorykeeper.services;

import com.heftyb.inventorykeeper.exceptions.ResourceNotFoundException;
import com.heftyb.inventorykeeper.models.Role;
import com.heftyb.inventorykeeper.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "roleService")
public class RoleServiceImp implements RoleService {
    @Autowired
    private RoleRepository roleRepo;


    @Override
    public List<Role> findAll() {

        List<Role> roles = new ArrayList<>();
        roleRepo.findAll().forEach(roles::add);
        return roles;
    }

    @Override
    public Role findById(long id) {
        return roleRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role id " + id + " not found!"));
    }

    @Override
    public Role findByName(String name) {
        Role r = roleRepo.findByRoleIgnoreCase(name);
        if (r != null) {
            return r;
        } else {
            throw new ResourceNotFoundException("Role id " + name + " not found!");
        }
    }
}
