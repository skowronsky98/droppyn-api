package com.droppynapi.converter;

import com.droppynapi.dto.ShoeDTO;
import com.droppynapi.model.Shoe;

public class ShoeConverter {
//    public static Shoe toEntity(ShoeDTO dto) {
//        return new Shoe(dto.getId(),
//                dto.getModel(),
//                dto.getBrand(),
//                dto.getMedia());
//    }

    public static ShoeDTO toDTO(Shoe entity){
        return new ShoeDTO(entity.get_id(),
                entity.getModel(),
                BrandConverter.toDTO(entity.getBrand()),
                entity.getMedia());
    }
}
