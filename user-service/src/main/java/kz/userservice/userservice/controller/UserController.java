package kz.userservice.userservice.controller;

import kz.userservice.userservice.models.dtos.UserToAuthService;
import kz.userservice.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/to-auth")
    ResponseEntity<UserToAuthService> getUser(@RequestParam(name = "id") Long id){
        return ResponseEntity.ok(service.toAuthService(id));
    }
}
