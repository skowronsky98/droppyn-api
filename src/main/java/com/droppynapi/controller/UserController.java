package com.droppynapi.controller;

import com.droppynapi.converter.UserConverter;
import com.droppynapi.dto.user.RegisterUserDTO;
import com.droppynapi.dto.user.RoleToUserForm;
import com.droppynapi.dto.user.UserDTO;
import com.droppynapi.dto.user.UserUpdateDTO;
import com.droppynapi.model.Role;
import com.droppynapi.model.User;
import com.droppynapi.repo.UserRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody RegisterUserDTO registerUserDTO){
        User user = userRepo.registerUser(registerUserDTO);

        if(user != null)
            return ResponseEntity.ok(UserConverter.toDTO(user));

        return ResponseEntity.status(BAD_REQUEST).body(null);
    }

    @GetMapping
    public UserDTO getUserById(@RequestParam(value = "id") String id){
        return UserConverter.toDTO(userRepo.getUserById(id));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username){
        return ResponseEntity.ok().body(UserConverter.toDTO(userRepo.getUserByUsername(username)));
    }

    @PostMapping("/role")
    public ResponseEntity<Role> saveRole(Role role){
        return ResponseEntity.ok()
                .body(userRepo.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<Role> addRoleToUser(@RequestBody RoleToUserForm form){
        userRepo.addRoleToUser(form.getUsername(),form.getRoleName());
        return ResponseEntity.ok()
                .build();
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        Map<String,String> tokens = null;
        try {
            tokens = userRepo.refreshToken(authorizationHeader);
        } catch (Exception e){
            log.error("Error logging in: {}", e.getMessage());
            response.setHeader("error", e.getMessage());
            response.setStatus(UNAUTHORIZED.value());
            Map<String,String> error = new HashMap<>();
            error.put("error_message", e.getMessage());
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }

        if(tokens != null){
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), tokens);
        } else {
            response.sendError(UNAUTHORIZED.value());
        }


    }

    @PutMapping
    public UserDTO updateUser(@RequestBody UserUpdateDTO userUpdateDTO){
        return UserConverter.toDTO(userRepo.updateUser(userUpdateDTO));
    }


}


