package com.epam.library.domain;

import org.springframework.security.core.GrantedAuthority;

public enum UserType implements GrantedAuthority {
    ADMINISTRATOR, LIBRARIAN, READER;

    @Override
    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
