package com.example.mobileaccessoriesbackend.controller;

import com.example.mobileaccessoriesbackend.jwt.AuthenticationRequest;
import com.example.mobileaccessoriesbackend.services.interfaces.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final IUserService userService;

    public AuthController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthenticationRequest request){
        return userService.signIn(request);
    }
}
