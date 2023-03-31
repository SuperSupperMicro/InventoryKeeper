package com.heftyb.inventorykeeper.services;

import com.heftyb.inventorykeeper.config.AuthUserDetails;
import com.heftyb.inventorykeeper.models.CurrentUser;
import com.heftyb.inventorykeeper.models.User;
import com.heftyb.inventorykeeper.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CurrentUserDetailsService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());
        CurrentUser currentUser = buildUserForAuthentication(user, authorities);
        return new AuthUserDetails(currentUser);
    }

    public UserDetails loadUserById(long id) {
        User user = userService.findUserById(id);
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
            authorities.add(new SimpleGrantedAuthority(userRole.getRole().toString()));
        }
        return new ArrayList<>(authorities);
    }
}
