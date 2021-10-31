package com.droppynapi.service;

import com.droppynapi.dto.user.UserUpdateDTO;
import com.droppynapi.exception.ResourceNotFoundException;
import com.droppynapi.model.SizeChart;
import com.droppynapi.model.User;
import com.droppynapi.repo.SizeChartRepo;
import com.droppynapi.repo.UserRepo;
import com.droppynapi.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserRepo {
    @Autowired
    private UserDao userDao;

    @Autowired
    private SizeChartRepo sizeChartRepo;

    @Override
    public User getUserById(String id){
        return userDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no user with id: "+id));
    }

    @Override
    public User updateUser(UserUpdateDTO userUpdateDTO) {
        if(userUpdateDTO.getId() != null) {
            User user = getUserById(userUpdateDTO.getId());

            user.setUsername(userUpdateDTO.getUsername());
            user.setFirstname(userUpdateDTO.getFirstname());
            user.setSurname(userUpdateDTO.getSurname());
            if(userUpdateDTO.getPhone().length() == 6)
                user.setPhone(userUpdateDTO.getPhone());
            user.setPhotoURL(userUpdateDTO.getPhotoURL());
            user.setBio(userUpdateDTO.getBio());

            if(userUpdateDTO.getIdDefultSize() != null){
                SizeChart size = sizeChartRepo.getSizeChartById(userUpdateDTO.getIdDefultSize());
                user.setDefultSize(size);
            }
            return userDao.save(user);
        }
        return null;
    }

    @Override
    public User saveUser(User user) {
        return userDao.save(user);
    }

    @Override
    public User createUser(User user){

        user.set_id(null);
        return userDao.save(user);
    }

}
