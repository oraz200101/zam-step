package com.example.authserver.jwt;

import com.example.authserver.models.enums.Role;
import com.example.authserver.services.AuthService;
import com.example.authserver.services.props.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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

    private       SecretKey     key;
    private final AuthService   authService;
    private final JwtProperties jwtProperties;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
    }

    public String createAccessToken(final String email, final Set<Role> roles) {

        Claims claims = Jwts.claims()
                            .subject(email)
                            .add("roles", resolveRoles(roles))
                            .build();

        Instant validity = Instant.now().plus(jwtProperties.getAccess(), ChronoUnit.HOURS);

        return Jwts.builder()
                   .claims(claims)
                   .expiration(Date.from(validity))
                   .signWith(key)
                   .compact();
    }

    public String createRefreshToken(final String email, final Set<Role> roles) {
        Claims claims = Jwts.claims()
                            .subject(email)
                            .add("roles", resolveRoles(roles))
                            .build();

        Instant validity = Instant.now().plus(jwtProperties.getRefresh(), ChronoUnit.HOURS);

        return Jwts.builder()
                   .claims(claims)
                   .expiration(Date.from(validity))
                   .signWith(key)
                   .compact();
    }

    private List<String> resolveRoles(final Set<Role> roles) {
        return roles.stream()
                    .map(Enum::name)
                    .collect(Collectors.toList());
    }

    public boolean isValid(final String token) throws ExpiredJwtException{
            Jws<Claims> claims = Jwts.parser()
                                     .verifyWith(key)
                                     .build()
                                     .parseSignedClaims(token);

            return claims.getPayload()
                         .getExpiration()
                         .after(new Date());
    }

    private String getId(final String token) {
        return Jwts.parser()
                   .verifyWith(key)
                   .build()
                   .parseSignedClaims(token)
                   .getPayload()
                   .get("id", String.class);
    }

    private String getUsername(final String token) {
        return Jwts.parser()
                   .verifyWith(key)
                   .build()
                   .parseSignedClaims(token)
                   .getPayload()
                   .getSubject();
    }

    public Authentication getAuthentication(final String token) {
        String      username    = getUsername(token);
        UserDetails userDetails = authService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
