package com.heftyb.inventorykeeper.services;

import com.heftyb.inventorykeeper.exceptions.ResourceNotFoundException;
import com.heftyb.inventorykeeper.models.User;
import com.heftyb.inventorykeeper.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "userService")
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        userRepo.findAll()
                .iterator()
                .forEachRemaining(users::add);
        return users;
    }

    @Override
    public User findUserById(long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
    }

    @Override
    public User findByUsername(String name) {
        User u = userRepo.findByUsername(name);
        if (u == null) { throw new ResourceNotFoundException("Username " + name + " not found!"); }
        return u;
    }

    @Override
    public User findUserByEmail(String email) {
        User u =  userRepo.findByEmail(email);
        if (u == null) { throw new ResourceNotFoundException("Email " + email + " not found!"); }
        return u;
    }
}
