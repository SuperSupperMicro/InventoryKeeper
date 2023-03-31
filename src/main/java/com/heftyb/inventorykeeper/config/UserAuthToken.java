package com.heftyb.inventorykeeper.config;

import com.heftyb.inventorykeeper.exceptions.ResourceNotFoundException;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * An implementation of an authentication token to hold our custom AuthUserDetails
 */
public class UserAuthToken extends AbstractAuthenticationToken {

    private  UserDetails principal;

    public UserAuthToken(UserDetails principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
    }

    public long getAuthenticatedUserId() {
        if (!(principal instanceof AuthUserDetails)) {
            throw new ClassCastException("Error: UserDetails principal is  not instanceof AuthUserDetails"
                    + "\nconfig.AuthUserAuthentication.getAuthenticatedUserId()");
        }
        return ((AuthUserDetails) principal).getUser().getUserId();
    }

    @Override
    public Object getCredentials() { return null; }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
