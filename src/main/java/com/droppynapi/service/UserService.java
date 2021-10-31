package com.droppynapi.service;

import com.droppynapi.dao.RoleDao;
import com.droppynapi.dto.user.UserUpdateDTO;
import com.droppynapi.exception.ResourceNotFoundException;
import com.droppynapi.model.Role;
import com.droppynapi.model.SizeChart;
import com.droppynapi.model.User;
import com.droppynapi.repo.SizeChartRepo;
import com.droppynapi.repo.UserRepo;
import com.droppynapi.dao.UserDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Transient;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserService implements UserRepo, UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private SizeChartRepo sizeChartRepo;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findUserByUsername(username);
        if(user == null){
            log.error("User {} not found in database",username);
            throw new UsernameNotFoundException("User not found in database");
        } else {
            log.error("User {} found in database",username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }

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
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userDao.save(user);
        }
        return null;
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userDao.findUserByUsername(username);
        Role role = roleDao.findRoleByName(roleName);
        user.getRoles().add(role);
        userDao.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    @Override
    public Role saveRole(Role role) {
        return roleDao.save(role);
    }

    @Override
    public User createUser(User user){

        user.set_id(null);
        return userDao.save(user);
    }


}
