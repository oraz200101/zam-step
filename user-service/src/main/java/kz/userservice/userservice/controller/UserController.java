package kz.userservice.userservice.controller;

import kz.userservice.userservice.models.dtos.UserRegistrationRequest;
import kz.userservice.userservice.models.dtos.UserToAuthService;
import kz.userservice.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/to-auth")
    ResponseEntity<UserToAuthService> getUser(@RequestParam(name = "id") Long id){
        return ResponseEntity.ok(service.toAuthService(id));
    }

    @PostMapping("/create")
    ResponseEntity<?> createUser(@RequestBody UserRegistrationRequest request){
        service.createUser(request);
        return ResponseEntity.ok(request);
    }

}
