package com.droppynapi.repo;

import com.droppynapi.dto.user.UserUpdateDTO;
import com.droppynapi.model.Role;
import com.droppynapi.model.User;

public interface UserRepo {
    User createUser(User user);
    User getUserById(String id);
    User updateUser(UserUpdateDTO userUpdateDTO);
    User saveUser(User user);
    void addRoleToUser(String username, String roleName);
    User getUserByUsername(String username);

    Role saveRole(Role role);
}
