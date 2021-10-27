package com.droppynapi.converter;

import com.droppynapi.dto.UserDTO;
import com.droppynapi.dto.UserUpdateDTO;
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
                dto.getBio(),
                dto.getDefultSize());
    }

    public static UserDTO toDTO(User entity){
        return new UserDTO(entity.get_id(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getFirstname(),
                entity.getSurname(),
                entity.getPhone(),
                entity.getPhotoURL(),
                (entity.getBio() != null) ? entity.getBio() : "",
                entity.getDefultSize());
    }

}
