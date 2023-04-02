package com.heftyb.inventorykeeper.config;

import com.heftyb.inventorykeeper.Auth.UserAuthToken;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * This allows us to change the default values that get inserted into our
 * auditing fields when making changes to the database. Spring security uses the authenticated
 * User's username, but since our database was already set up expecting a long/bigint for the user's FK value
 * we override the getCurrentAuditor method and return the User's userId instead.
 */
public class AuditorAwareImp implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        Optional<Authentication> auth = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
        Long authUserId = null;
        if (auth.isPresent()) {
            authUserId = ((UserAuthToken) auth.get()).getAuthenticatedUserId();
        }
        return Optional.ofNullable(authUserId);
    }
}
