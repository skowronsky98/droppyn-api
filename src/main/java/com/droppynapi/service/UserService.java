package com.droppynapi.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.droppynapi.dao.RoleDao;
import com.droppynapi.dao.UserDao;
import com.droppynapi.dto.user.RegisterUserDTO;
import com.droppynapi.dto.user.UserUpdateDTO;
import com.droppynapi.exception.ResourceNotFoundException;
import com.droppynapi.model.Role;
import com.droppynapi.model.SizeChart;
import com.droppynapi.model.User;
import com.droppynapi.repo.SizeChartRepo;
import com.droppynapi.repo.UserRepo;
import com.droppynapi.utills.Utills;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
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
            log.info("User {} found in database",username);
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

//            user.setUsername(userUpdateDTO.getUsername());
            user.setFirstname(userUpdateDTO.getFirstname());
            user.setSurname(userUpdateDTO.getSurname());
            if(userUpdateDTO.getPhone().length() == 9)
                user.setPhone(userUpdateDTO.getPhone());
            user.setPhotoURL(userUpdateDTO.getPhotoURL());
            user.setBio(userUpdateDTO.getBio());

            if(userUpdateDTO.getIdDefultSize() != null){
                SizeChart size = sizeChartRepo.getSizeChartById(userUpdateDTO.getIdDefultSize());
                user.setDefultSize(size);
            }
            //user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userDao.save(user);
        }
        return null;
    }

    @Override
    public User saveUser(User user) {
        return userDao.save(user);
    }

//    @Override
//    public User saveUser(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return userDao.save(user);
//    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userDao.findUserByUsername(username);
        Role role = roleDao.findRoleByName(roleName);
        user.getRoles().add(role);
//        userDao.save(user);
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
    public User registerUser(RegisterUserDTO user){

        if(user != null && user.getUsername() != null && !user.getUsername().isEmpty() && user.getEmail() != null && !user.getEmail().isEmpty() && user.getPassword() != null && !user.getPassword().isEmpty()) {
            if(userDao.findUserByUsername(user.getUsername()) != null)
                return null;

            User newUser = new User();
            newUser.setUsername(user.getUsername());
            newUser.setEmail(user.getEmail());
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));

            newUser.getRoles().add(roleDao.findRoleByName(Utills.ROLE_USER));

            return userDao.save(newUser);
        }

        return null;
    }

    @Override
    public Map<String,String> refreshToken(String authorizationHeader) {
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = userDao.findUserByUsername(username);

                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() +  24 * 60 * 60 * 1000))
                        .withIssuer("Droppyn")
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String,String> tokens = new HashMap<>();
                tokens.put("access_token",access_token);
                tokens.put("refresh_token",refresh_token);
                return tokens;

            }catch (Exception e) {
                throw e;
            }

        }
        return null;


    }

    @Override
    public Map<String, String> generateToken() {

        return null;
    }

}
