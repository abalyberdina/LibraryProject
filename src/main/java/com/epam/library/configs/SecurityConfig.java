package com.epam.library.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.epam.library.domain.UserType;

@Configuration
@EnableWebSecurity
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String REMOVE_OPER = "remove/**";
    private static final String ADD_OPER = "add/**";
    private static final String UPDATE_OPER = "update/**";
    private static final String ORDER_BOOK_OPER = "order/**";

    private static final String HOME_URL = "/";
    private static final String LOGIN_URL = "/login";
    private static final String LOGOUT_URL = "/logout";
    private static final String REGISTRATION_URL = "/registration";

    private static final String MANAGE_BOOK_URL = "/books/";
    private static final String MANAGE_AUTHORS_URL = "/authors/";
    private static final String MANAGE_USERS_URL = "/users/";
    private static final String MANAGE_GENRES_URL = "/genres/";
    private static final String MANAGE_PROFILE_URL = "/myprofile/**";
    private static final String MANAGE_ORDER_URL = "/orders/";
    private static final String MANAGE_MYORDER_URL = "/myorders/";

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(LOGIN_URL)
                .anonymous()
                .antMatchers(REGISTRATION_URL)
                .anonymous()
                .antMatchers(LOGOUT_URL)
                .authenticated()
                .antMatchers(MANAGE_PROFILE_URL)
                .authenticated()
                .antMatchers(MANAGE_BOOK_URL + ADD_OPER).hasRole(UserType.LIBRARIAN.name())
                .antMatchers(MANAGE_BOOK_URL + REMOVE_OPER).hasRole(UserType.LIBRARIAN.name())
                .antMatchers(MANAGE_BOOK_URL + UPDATE_OPER).hasAnyRole(UserType.LIBRARIAN.name())
                .antMatchers(MANAGE_BOOK_URL + ORDER_BOOK_OPER).authenticated()
                .antMatchers(MANAGE_GENRES_URL + ADD_OPER).hasRole(UserType.LIBRARIAN.name())
                .antMatchers(MANAGE_GENRES_URL + REMOVE_OPER).hasRole(UserType.LIBRARIAN.name())
                .antMatchers(MANAGE_GENRES_URL + UPDATE_OPER).hasAnyRole(UserType.LIBRARIAN.name())
                .antMatchers(MANAGE_AUTHORS_URL + ADD_OPER).hasRole(UserType.LIBRARIAN.name())
                .antMatchers(MANAGE_AUTHORS_URL + REMOVE_OPER).hasRole(UserType.LIBRARIAN.name())
                .antMatchers(MANAGE_AUTHORS_URL + UPDATE_OPER)
                .hasRole(UserType.LIBRARIAN.name())
                .antMatchers(MANAGE_USERS_URL + "**")
                .hasRole(UserType.ADMINISTRATOR.name())
                .antMatchers(MANAGE_ORDER_URL + "**").hasAnyRole(UserType.LIBRARIAN.name())
                .antMatchers(MANAGE_MYORDER_URL + "**").authenticated().and()

                .formLogin().loginPage(LOGIN_URL).loginProcessingUrl(LOGIN_URL)
                .failureUrl(LOGIN_URL + "?error=1").defaultSuccessUrl(HOME_URL).and()

                .logout().logoutUrl(LOGOUT_URL).logoutSuccessUrl(HOME_URL)
                .deleteCookies("JSESSIONID").and();
    }
}
