package com.droppynapi.converter;

import com.droppynapi.dto.OfferDTO;
import com.droppynapi.dto.SizeChartDTO;
import com.droppynapi.model.Offer;
import com.droppynapi.model.SizeChart;

public class SizeChartConverter {
//    public static SizeChart toEntity(SizeChartDTO dto) {
//        return new SizeChart(dto.getId(),
//                dto.getUs(),
//                dto.getUk(),
//                dto.getEu(),
//                dto.getType(),
//                dto.getBrand());
//    }

    public static SizeChartDTO toDTO(SizeChart entity){
        return new SizeChartDTO(entity.get_id(),
                entity.getUs(),
                entity.getUk(),
                entity.getEu(),
                entity.getType(),
                entity.getBrand().get_id());
    }
}
