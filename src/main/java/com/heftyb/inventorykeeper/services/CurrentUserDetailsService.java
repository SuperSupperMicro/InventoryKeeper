package com.heftyb.inventorykeeper.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface CurrentUserDetailsService {
    UserDetails loadUserById(long id);
    UserDetails loadUserByIdAsString(String id);
}
