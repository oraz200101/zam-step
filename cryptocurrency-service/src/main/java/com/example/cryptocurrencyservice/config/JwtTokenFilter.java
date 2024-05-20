package com.example.cryptocurrencyservice.config;


import com.example.cryptocurrencyservice.rest_client.AuthServerClient;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;


@RequiredArgsConstructor
public class JwtTokenFilter extends GenericFilterBean {

    private final AuthServerClient authServerClient;

    @Override
    @SneakyThrows
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) {

        String bearerToken = ((HttpServletRequest) servletRequest)
                .getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            bearerToken = bearerToken.substring(7);
        }

        try {
            if (bearerToken != null && authServerClient.tokenIsValid(bearerToken)) {
                Authentication authentication = authServerClient.getAuthentication(bearerToken);
                if (authentication != null) {
                    SecurityContextHolder.getContext()
                                         .setAuthentication(authentication);
                }
            }
        } catch (Exception ignored) {
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
