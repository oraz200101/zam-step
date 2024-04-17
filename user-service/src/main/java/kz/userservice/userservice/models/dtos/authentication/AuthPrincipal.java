package kz.userservice.userservice.models.dtos.authentication;

import kz.userservice.userservice.models.enums.Role;

import java.security.Principal;
import java.util.Set;

public class AuthPrincipal implements Principal {
    private Long id;
    private String email;
    private String password;
    private Set<Role> roles;
    private boolean enabled;
    private boolean credentialsNonExpired;
    private boolean accountNonLocked;
    private boolean accountNonExpired;

    @Override
    public String getName() {
        return "";
    }
}
