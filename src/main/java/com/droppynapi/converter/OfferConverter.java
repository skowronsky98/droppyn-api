package com.droppynapi.converter;

import com.droppynapi.dto.OfferDTO;
import com.droppynapi.dto.UserDTO;
import com.droppynapi.model.Offer;
import com.droppynapi.model.User;

public class OfferConverter {
//    public static Offer toEntity(OfferDTO dto) {
//        return new Offer(dto.getId(),
//                dto.getPrice(),
//                dto.getActive(),
//                dto.getDeleted(),
//                dto.getBio(),
//                dto.getShoe(),
//                dto.getSizeChart(),
//                dto.getUserDTO());
//    }

    public static OfferDTO toDTO(Offer entity){
        return new OfferDTO(entity.get_id(),
                entity.getPrice(),
                entity.getActive(),
                entity.getDeleted(),
                entity.getBio(),
                ShoeConverter.toDTO(entity.getShoe()),
                SizeChartConverter.toDTO(entity.getSizeChart()),
                UserConverter.toDTO(entity.getUser()));
    }
}
