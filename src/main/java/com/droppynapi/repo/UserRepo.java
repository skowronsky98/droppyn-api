package com.droppynapi.repo;

import com.droppynapi.dto.user.RegisterUserDTO;
import com.droppynapi.dto.user.UserUpdateDTO;
import com.droppynapi.model.Role;
import com.droppynapi.model.User;

import java.util.Map;

public interface UserRepo {
    User registerUser(RegisterUserDTO user);
    User getUserById(String id);
    User updateUser(UserUpdateDTO userUpdateDTO);
    User saveUser(User user);


    void addRoleToUser(String username, String roleName);
    User getUserByUsername(String username);
    Role saveRole(Role role);
    Map<String,String> refreshToken(String authHeader);
    Map<String,String> generateToken();

}
