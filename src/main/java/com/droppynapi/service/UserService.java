package com.droppynapi.service;

import com.droppynapi.exception.ResourceNotFoundException;
import com.droppynapi.model.User;
import com.droppynapi.repo.UserRepo;
import com.droppynapi.repodb.UserDatabaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserRepo {
    @Autowired
    private UserDatabaseRepo userDatabaseRepo;

    @Override
    public User getUserById(String id){
        return userDatabaseRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no user with id: "+id));
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public User createUser(User user){
        return userDatabaseRepo.save(user);
    }

}
