package com.heftyb.inventorykeeper.services;

import com.heftyb.inventorykeeper.Auth.AuthUserDetails;
import com.heftyb.inventorykeeper.exceptions.ResourceNotFoundException;
import com.heftyb.inventorykeeper.models.CurrentUser;
import com.heftyb.inventorykeeper.models.User;
import com.heftyb.inventorykeeper.models.UserRole;
import com.heftyb.inventorykeeper.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Service(value = "currentUserDetailsService")
public class CurrentUserDetailsServiceImp implements CurrentUserDetailsService, UserDetailsService {
    @Autowired
    UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) { throw new ResourceNotFoundException("Username " + username + " not found!"); }
        List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());
        CurrentUser currentUser = buildUserForAuthentication(user, authorities);

        return new AuthUserDetails(currentUser);
    }
    @Override
    public UserDetails loadUserById(long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
        List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());
        CurrentUser currentUser = buildUserForAuthentication(user, authorities);
        return new AuthUserDetails(currentUser);
    }

    @Override
    public UserDetails loadUserByIdAsString(String idString) {
        long id;
        try {
            id = Long.parseLong(idString);
        } catch (NumberFormatException e) {
            System.out.println(e.getStackTrace());
            throw new BadCredentialsException("Error loading user by userIdAsString");
        }
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
        List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());
        CurrentUser currentUser = buildUserForAuthentication(user, authorities);

        return new AuthUserDetails(currentUser);
    }

    private CurrentUser buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        long userId = user.getUserId();
        String username = user.getUsername();
        String email = user.getEmail();
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        return new CurrentUser(userId, username, email, "null", enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
        Set<GrantedAuthority> authorities = new HashSet<>();

        for (UserRole userRole: userRoles) {
            /**
             * Add on the user's roles from the database, we also add on springs "ROLE_" prefix
             */
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userRole.getRole().getRole()));
        }
        return new ArrayList<>(authorities);
    }
}
