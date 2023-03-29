package com.heftyb.inventorykeeper.services;

import com.heftyb.inventorykeeper.config.AuthUserPrincipal;
import com.heftyb.inventorykeeper.exceptions.ResourceNotFoundException;
import com.heftyb.inventorykeeper.models.User;
import com.heftyb.inventorykeeper.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userService")
public class UserServiceImp implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public List<User> findAll() {
//        return null;
        List<User> users = new ArrayList<>();
        userRepo.findAll()
                .iterator()
                .forEachRemaining(users::add);
        return users;
    }

    @Override
    public User findUserById(long id) {
//        return null;
        return userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
    }

    @Override
    public User findUserByGID(String GID) {
//        return null;
        return userRepo.findByGoogleId(GID);
//                .orElseThrow(() -> new ResourceNotFoundException("User id " + GID + " not found!"));;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepo.findByEmail(email);
//        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findById(new Long(username))
                .orElseThrow(() -> new ResourceNotFoundException("User id " + username + " not found!"));

//        return new AuthUserPrincipal(user);
        return null;
    }

    public AuthUserPrincipal loadUserById(long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
        return new AuthUserPrincipal(user);
    }

//    private List<GrantedAuthority> buildUserAuthority(Set<UserRole>)
}
