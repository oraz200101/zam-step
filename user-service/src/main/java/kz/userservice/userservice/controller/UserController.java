package kz.userservice.userservice.controller;

import kz.userservice.userservice.models.dtos.UserProfileDto;
import kz.userservice.userservice.models.dtos.UserUpdateRequestDto;
import kz.userservice.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PutMapping("/profile")
    public ResponseEntity<UserProfileDto> updateProfile(UserUpdateRequestDto request){
        return ResponseEntity.ok(service.updateProfile(request));
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDto> getProfile(){
        return ResponseEntity.ok(service.getProfile());
    }
}
