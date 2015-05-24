package com.epam.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.epam.library.repository.LoginDataRepository;
import com.epam.library.support.SecurityUserDetails;

@Service
public class LoginDataService implements UserDetailsService {
    private LoginDataRepository loginDataRepository;

    @Autowired
    public LoginDataService(LoginDataRepository loginDataRepository) {
        this.loginDataRepository = loginDataRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String name)
            throws UsernameNotFoundException {
        return new SecurityUserDetails(loginDataRepository.findByLogin(name));
    }
}
