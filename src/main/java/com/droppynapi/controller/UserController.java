package com.droppynapi.controller;

import com.droppynapi.converter.UserConverter;
import com.droppynapi.dto.UserDTO;
import com.droppynapi.model.User;
import com.droppynapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public UserDTO createUser(@RequestBody User user){
        return UserConverter.toDTO(userService.createUser(user));
    }

    @GetMapping
    public UserDTO getUserById(@RequestParam(value = "id") String id){
        return UserConverter.toDTO(userService.getUserById(id));
    }

}
