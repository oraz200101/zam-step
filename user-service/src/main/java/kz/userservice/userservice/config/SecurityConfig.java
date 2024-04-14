package kz.userservice.userservice.config;

import kz.userservice.userservice.rest_client.AuthServerClient;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthServerClient authServerClient;
    @Bean
    @SneakyThrows
    public SecurityFilterChain filterChain(final HttpSecurity httpSecurity
    ) {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement ->
                                           sessionManagement
                                                   .sessionCreationPolicy(
                                                           SessionCreationPolicy.STATELESS
                                                   )
                )
                .exceptionHandling(configurer ->
                                           configurer.authenticationEntryPoint(
                                                             (request, response, exception) -> {
                                                                 response.setStatus(
                                                                         HttpStatus.UNAUTHORIZED
                                                                                 .value()
                                                                 );
                                                                 response.getWriter()
                                                                         .write("Unauthorized.");
                                                             })
                                                     .accessDeniedHandler(
                                                             (request, response, exception) -> {
                                                                 response.setStatus(
                                                                         HttpStatus.FORBIDDEN
                                                                                 .value()
                                                                 );
                                                                 response.getWriter()
                                                                         .write("Unauthorized.");
                                                             }))
                .authorizeHttpRequests(configurer ->
                                               configurer.anyRequest().permitAll())
                .anonymous(AbstractHttpConfigurer::disable)
                .addFilterBefore(new JwtTokenFilter(authServerClient),
                                 UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
