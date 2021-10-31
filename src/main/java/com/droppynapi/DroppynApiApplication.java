package com.droppynapi;

import com.droppynapi.model.Role;
import com.droppynapi.repo.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DroppynApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DroppynApiApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserRepo userRepo){
        return args -> {
//          userRepo.saveRole(new Role(null,"ROLE_USER"));
//          userRepo.saveRole(new Role(null,"ROLE_MANAGER"));
//          userRepo.saveRole(new Role(null,"ROLE_ADMIN"));
//          userRepo.saveRole(new Role(null,"ROLE_SUPERADMIN"));

        };
    }
}
