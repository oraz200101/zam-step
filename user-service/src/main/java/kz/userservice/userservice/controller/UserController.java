package kz.userservice.userservice.controller;

import kz.userservice.userservice.models.dtos.UserToAuthService;
import kz.userservice.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/to-auth")
    ResponseEntity<UserToAuthService> getUser(@RequestParam("email") String email){
        return ResponseEntity.ok(service.toAuthService(email));
    }

}
