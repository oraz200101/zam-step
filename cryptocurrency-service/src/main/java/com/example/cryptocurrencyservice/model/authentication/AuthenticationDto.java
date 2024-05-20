package com.example.cryptocurrencyservice.model.authentication;

import com.example.cryptocurrencyservice.model.enums.Role;
import lombok.ToString;
import org.springframework.security.core.Authentication;

import java.util.Set;

@ToString
public class AuthenticationDto implements Authentication {
    private String        name;
    private String        details;
    private AuthPrincipal principal;
    private String        credentials;
    private Set<Role>     authorities;
    private boolean       authenticated;


    @Override
    public Set<Role> getAuthorities() {
        return this.authorities;
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getDetails() {
        return this.details;
    }

    @Override
    public AuthPrincipal getPrincipal() {
        return this.principal;
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
