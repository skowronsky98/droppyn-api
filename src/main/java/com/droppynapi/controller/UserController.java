package com.droppynapi.controller;

import com.droppynapi.converter.UserConverter;
import com.droppynapi.dto.user.UserDTO;
import com.droppynapi.dto.user.UserUpdateDTO;
import com.droppynapi.model.User;
import com.droppynapi.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PutMapping
    public UserDTO updateUser(@RequestBody UserUpdateDTO userUpdateDTO){
        return UserConverter.toDTO(userRepo.updateUser(userUpdateDTO));
    }
}
