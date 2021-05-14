package com.droppynapi.converter;

import com.droppynapi.dto.ShoeDTO;
import com.droppynapi.dto.UserDTO;
import com.droppynapi.model.Shoe;
import com.droppynapi.model.User;

public class ShoeConverter {
    public static Shoe toEntity(ShoeDTO dto) {
        return new Shoe(dto.getId(),
                dto.getModel(),
                dto.getBrand(),
                dto.getMedia(),
                dto.getFavorite());
    }

    public static ShoeDTO toDTO(Shoe entity){
        return new ShoeDTO(entity.get_id(),
                entity.getModel(),
                entity.getBrand(),
                entity.getMedia(),
                entity.getFavorite());
    }
}
