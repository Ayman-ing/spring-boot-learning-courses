package com.example.springSecurity.controllers;

import com.example.springSecurity.models.ApplicationUser;
import com.example.springSecurity.models.RegistrationDto;
import com.example.springSecurity.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationDto registrationDto){
        return authenticationService.registerUser(registrationDto.getUsername(), registrationDto.getPassword());
    }
}
