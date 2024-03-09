package com.example.authserver.jwt;

import com.example.authserver.models.enums.Role;
import com.example.authserver.services.props.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;

    private SecretKey key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
    }

    public String createAccessToken(final Long userId,
                                    final String email,
                                    final Set<Role> roles) {

        Claims claims = Jwts.claims()
                            .subject(email)
                            .add("userId", userId)
                            .add("roles", resolveRoles(roles))
                            .build();

        Instant validity = Instant.now()
                                  .plus(jwtProperties.getAccess(), ChronoUnit.HOURS);

        return Jwts.builder()
                   .claims(claims)
                   .expiration(Date.from(validity))
                   .signWith(key)
                   .compact();
    }

    public String createRefreshToken(final Long userId,
                                     final String email,
                                     final Set<Role> roles) {
        Claims claims = Jwts.claims()
                            .subject(email)
                            .add("userId", userId)
                            .add("roles", resolveRoles(roles))
                            .build();

        Instant validity = Instant.now()
                                  .plus(jwtProperties.getRefresh(), ChronoUnit.HOURS);

        return Jwts.builder()
                   .claims(claims)
                   .expiration(Date.from(validity))
                   .signWith(key)
                   .compact();
    }

    private List<String> resolveRoles(
            final Set<Role> roles
    ) {
        return roles.stream()
                    .map(Enum::name)
                    .collect(Collectors.toList());
    }


}
