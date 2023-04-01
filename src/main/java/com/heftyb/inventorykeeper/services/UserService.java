package com.heftyb.inventorykeeper.services;

import com.heftyb.inventorykeeper.models.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findByUsername(String name);
    User findUserById(long id);
    User findUserByGID(String GID);
    User findUserByEmail(String email);
}
