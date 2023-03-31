package com.heftyb.inventorykeeper.config;


import com.heftyb.inventorykeeper.services.CurrentUserDetailsService;
import heftytoken.HeftyToken;
import heftytoken.Token;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthFilter implements Filter {
    @Autowired
    CurrentUserDetailsService userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String headerString = ((HttpServletRequest) request).getHeader("Authorization");

        // Check for authorization headers
        if (headerString != null) {
            // Grab the token string
            String encodedToken = headerString.replace("Bearer ", "").trim();
            // Decode the HeftyToken String into a new Token() instance
            Token token = HeftyToken.decodeHeftyToken(encodedToken);
            // Get the user's info from the DB and load it into a new UserDetails()
            UserDetails userDetails = userService.loadUserById(Long.parseLong(token.getUserID()));
            // Create a new authentication principal with our custom CurrentUser
            Authentication authentication = new UserAuthToken(userDetails, userDetails.getAuthorities());

//            Authentication auth = new UsernamePasswordAuthenticationToken(
//                    userDetails.getUsername(),
//                    null,
//                    AuthorityUtils.createAuthorityList("ROLE_ADMIN"));

            // Load new authentication principal into the Security Context
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Proceed with the rest of the filter chain
        chain.doFilter(request, response);
    }
}



