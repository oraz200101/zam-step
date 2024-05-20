package com.example.cryptocurrencyservice.model.authentication;

import com.example.cryptocurrencyservice.model.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.security.Principal;
import java.util.Set;

@Getter
@Setter
public class AuthPrincipal implements Principal {
    private Long      id;
    private String    email;
    private String    password;
    private Set<Role> roles;
    private boolean   enabled;
    private boolean   credentialsNonExpired;
    private boolean   accountNonLocked;
    private boolean   accountNonExpired;

    @Override
    public String getName() {
        return this.email;
    }
}
