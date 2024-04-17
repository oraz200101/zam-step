package kz.userservice.userservice.rest_client;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.userservice.userservice.annotations.RestClientAnnotation;
import kz.userservice.userservice.models.dtos.authentication.AuthenticationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.Authentication;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.rmi.RemoteException;


@RestClientAnnotation
@RequiredArgsConstructor
public class AuthServerClientImpl implements AuthServerClient {

    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    @Override
    public Authentication getAuthentication(String token) {
        return restClient.get().uri(UriComponentsBuilder.fromPath("/produce-authentication")
                .queryParam("token", token)
                .toUriString()).exchange(((request, response) -> {
            if (response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(204))) {
                throw new RuntimeException();
            } else if (response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(200))) {
                return objectMapper.readValue(response.getBody(), AuthenticationDto.class);
            } else {
                throw new RemoteException();
            }
        }));
    }


    @Override
    public boolean tokenIsValid(String token) {
        return restClient.get().uri(UriComponentsBuilder.fromPath("/check-token")
                .queryParam("token", token)
                .toUriString()).exchange(((request, response) -> {
            if (response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(204))) {
                throw new RuntimeException();
            } else if (response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(200))) {
                return objectMapper.readValue(response.getBody(), Boolean.class);
            } else {
                throw new RemoteException();
            }
        }));
    }
}
