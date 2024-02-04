package com.example.springSecurity.services;

import com.example.springSecurity.models.ApplicationUser;
import com.example.springSecurity.models.Role;
import com.example.springSecurity.repositories.RoleRepository;
import com.example.springSecurity.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    public ApplicationUser registerUser(String username,String password){
        String encodedPassword = encoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);

        return userRepository.save(new ApplicationUser(2,username,encodedPassword,authorities));
    }
}
