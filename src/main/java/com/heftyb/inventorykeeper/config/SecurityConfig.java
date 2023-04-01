package com.heftyb.inventorykeeper.config;


import com.heftyb.inventorykeeper.Auth.AuthFilter;
import com.heftyb.inventorykeeper.services.CurrentUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.ConcurrentSessionFilter;


/**
 * A custom security configuration for Spring that applies our filter and
 * requires authentication for every request
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private CurrentUserDetailsService userDetailsService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests()
            .anyRequest()
            .authenticated()
            .and()
            .cors()
            .and().csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .logout().disable()
            .addFilterAfter(new AuthFilter(userDetailsService), ConcurrentSessionFilter.class);
//            .exceptionHandling()
//            .and()
//            .authorizeHttpRequests().antMatchers("/sdkjlsd/**").permitAll()
//            .antMatchers("/jhasdj/**").permitAll()
//            .anyRequest().authenticated();

        return http.build();
    }
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().
//    }
}
