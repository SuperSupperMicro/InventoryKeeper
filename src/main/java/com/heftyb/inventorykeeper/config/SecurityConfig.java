package com.heftyb.inventorykeeper.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception {
        http.authorizeHttpRequests()
                .anyRequest().permitAll()
                .and()
                .cors().and().csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .logout().disable();
//                .exceptionHandling()
//                .authenticationEntryPoint(unauthorizedHandler)
//                .and()
//                .authorizeHttpRequests().antMatchers("/sdkjlsd/**").permitAll()
//                .antMatchers("/jhasdj/**").permitAll()
//                .anyRequest().authenticated();

        return http.build();
    }
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().
//    }
}
