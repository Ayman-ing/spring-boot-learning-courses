package com.example.springSecurity;

import com.example.springSecurity.models.ApplicationUser;
import com.example.springSecurity.models.Role;
import com.example.springSecurity.repositories.RoleRepository;
import com.example.springSecurity.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringSecurity6Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurity6Application.class, args);
	}
	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository,
						  PasswordEncoder encoder){
			return args ->{
				if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
				Role adminRole = roleRepository.save(new Role("ADMIN"));
				roleRepository.save(new Role("USER"));
				Set<Role> roles = new HashSet<>();
				roles.add(adminRole);
				ApplicationUser admin = new ApplicationUser(1,"admin" ,
						encoder.encode("password"),
						roles);
				userRepository.save(admin);

			};
	}

}
