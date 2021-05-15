package com.droppynapi.service;

import com.droppynapi.exception.ResourceNotFoundException;
import com.droppynapi.model.User;
import com.droppynapi.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User getUserById(String id){
        return userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no user with id: "+id));
    }

    public User createUser(User user){
        return userRepo.save(user);
    }

}
