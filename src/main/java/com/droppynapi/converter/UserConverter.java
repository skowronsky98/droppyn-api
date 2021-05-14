package com.droppynapi.converter;

import com.droppynapi.dto.UserDTO;
import com.droppynapi.model.User;

public class UserConverter {
    public static User toEntity(UserDTO dto) {
        return new User(dto.getId(),
                dto.getUsername(),
                dto.getEmail(),
                dto.getFirstname(),
                dto.getSurname(),
                dto.getPhone(),
                dto.getPhotoURL(),
                dto.getDefultSize());
    }

    public static UserDTO toDTO(User entity){
        return new UserDTO(entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getFirstname(),
                entity.getSurname(),
                entity.getPhone(),
                entity.getPhotoURL(),
                entity.getDefultSize());
    }
}
