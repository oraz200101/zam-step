package com.example.authserver.models.entities;

import com.example.authserver.models.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "auths", schema = "auth_schema")
public class AuthEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "auth_role", schema = "auth_schema", joinColumns = @JoinColumn(name = "auth_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
}
