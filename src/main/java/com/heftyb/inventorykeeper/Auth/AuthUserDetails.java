package com.heftyb.inventorykeeper.Auth;

import com.heftyb.inventorykeeper.models.CurrentUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * An implementation of UserDetails to hold our custom CurrentUser
 *
 */
public class AuthUserDetails implements UserDetails {
    private CurrentUser user;

    public AuthUserDetails(CurrentUser user) {
        this.user = user;
    }

    public AuthUserDetails(String name) {
        CurrentUser u = new CurrentUser(name, "", new ArrayList<>());
        this.user = u;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

    public CurrentUser getUser() {
        return user;
    }

    public void setUser(CurrentUser user) {
        this.user = user;
    }
}
