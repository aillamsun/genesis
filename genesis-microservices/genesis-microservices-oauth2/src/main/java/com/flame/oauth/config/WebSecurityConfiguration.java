package com.flame.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

/*    @Override
    protected void configure(HttpSecurity http) throws Exception {
*//*        http.antMatcher("*").authorizeRequests() // all requests are protected by default
                .antMatchers("/", "/login**", "/webjars*").permitAll() // the home page and login endpoints are explicitly excluded
                .anyRequest().authenticated() // all other endpoints require an authenticated user
                .and()
                .csrf().disable();*//*

        http
                .formLogin().loginPage("/login").permitAll()
                .and()
                .requestMatchers()
                .antMatchers("/", "/login", "/oauth/authorize", "/oauth/confirm_access")
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();
    }*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

/*        auth.inMemoryAuthentication()
                .withUser("gavin")
                .password("gavin")
                .authorities("READ")
                .and()
                .withUser("gaven")
                .password("gaven")
                .authorities("READ", "WRITE");*/

        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

}
