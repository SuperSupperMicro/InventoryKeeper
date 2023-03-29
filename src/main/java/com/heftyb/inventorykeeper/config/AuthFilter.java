package com.heftyb.inventorykeeper.config;


import com.heftyb.inventorykeeper.services.UserServiceImp;
import heftytoken.HeftyToken;
import heftytoken.Token;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class AuthFilter implements Filter {
    @Autowired
    UserServiceImp userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String headerString = ((HttpServletRequest) request).getHeader("Authorization");

        if !(headerString != null) {
            String encodedToken = headerString.replace("Bearer ", "").trim();
            Token token = HeftyToken.decodeHeftyToken(encodedToken);
            AuthUserPrincipal authUserPrincipal = userService.loadUserById(Long.parseLong(token.getUserID()));

            //// TODO: make custom token extending the AbstractAuthenticationToken class
            ///  TODO: add in user's role form DB

            //        AuthUserAuthentication authentication = new AuthUserAuthentication(authUserPrincipal);
            Authentication auth = new UsernamePasswordAuthenticationToken(
                    authUserPrincipal.getUser(),
                    null,
                    AuthorityUtils.createAuthorityList("ROLE_ADMIN"));

            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        chain.doFilter(request, response);
    }
}



