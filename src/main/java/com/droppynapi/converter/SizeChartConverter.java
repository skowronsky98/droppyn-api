package com.droppynapi.converter;

import com.droppynapi.dto.OfferDTO;
import com.droppynapi.dto.SizeChartDTO;
import com.droppynapi.model.Offer;
import com.droppynapi.model.SizeChart;

public class SizeChartConverter {
    public static SizeChart toEntity(SizeChartDTO dto) {
        return new SizeChart(dto.getId(),
                dto.getEu(),
                dto.getUs(),
                dto.getUk(),
                dto.getBrand());
    }

    public static SizeChartDTO toDTO(SizeChart entity){
        return new SizeChartDTO(entity.get_id(),
                entity.getEu(),
                entity.getUs(),
                entity.getUk(),
                entity.getBrand());
    }
}
