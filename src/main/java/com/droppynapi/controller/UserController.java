package com.droppynapi.controller;

import com.droppynapi.converter.UserConverter;
import com.droppynapi.dto.user.RoleToUserForm;
import com.droppynapi.dto.user.UserDTO;
import com.droppynapi.dto.user.UserUpdateDTO;
import com.droppynapi.model.Role;
import com.droppynapi.model.User;
import com.droppynapi.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @PostMapping
    public UserDTO createUser(@RequestBody User user){
        return UserConverter.toDTO(userRepo.createUser(user));
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

    @PutMapping
    public UserDTO updateUser(@RequestBody UserUpdateDTO userUpdateDTO){
        return UserConverter.toDTO(userRepo.updateUser(userUpdateDTO));
    }


}


