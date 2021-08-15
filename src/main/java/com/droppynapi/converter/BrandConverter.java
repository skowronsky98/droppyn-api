package com.droppynapi.converter;

import com.droppynapi.dto.BrandDTO;
import com.droppynapi.model.Brand;

public class BrandConverter {
    public static Brand toEntity(BrandDTO dto) {
        return new Brand(dto.get_id(),
                dto.getName(),
                dto.getMedia());
    }

    public static BrandDTO toDTO(Brand entity){
        return new BrandDTO(entity.get_id(),
                entity.getName(),
                entity.getMedia());
    }
}
