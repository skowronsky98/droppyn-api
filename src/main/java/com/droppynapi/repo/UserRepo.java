package com.droppynapi.repo;

import com.droppynapi.model.User;

public interface UserRepo {
    User createUser(User user);
    User getUserById(String id);
    User updateUser(User user);
}
