package com.heftyb.inventorykeeper.Auth;


import com.heftyb.inventorykeeper.services.CurrentUserDetailsService;
import heftytoken.HeftyToken;
import heftytoken.Token;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;

/**
 * A filter to authorize authenticated tokens from Super Supper's User Service. This filter grabs the request
 * before it reaches the dispatch servlets and controllers.
 */
public class AuthFilter implements Filter {

    private CurrentUserDetailsService userDetailsService;

    public AuthFilter(CurrentUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        Authentication authentication = getToken((HttpServletRequest) request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private Authentication getToken(HttpServletRequest request) {
        String headerString = request.getHeader("Authorization");
        String encodedToken;
        Token tokenInfo = null;
        UserAuthToken token = null;
        if (headerString != null) {
            encodedToken = headerString.replace("Bearer ", "").trim();
            tokenInfo = HeftyToken.decodeHeftyToken(encodedToken);
            UserDetails userDetails = userDetailsService.loadUserByIdAsString(tokenInfo.getUserID());

            token = new UserAuthToken(userDetails, userDetails.getAuthorities());
            token.setAuthenticated(true);

            return token;

        }
        return null;
    }
}



