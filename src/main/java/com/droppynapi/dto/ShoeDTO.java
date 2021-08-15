package com.droppynapi.dto;

import com.droppynapi.model.Brand;
import com.droppynapi.model.Media;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoeDTO {
    private String _id;
    private String model;
    private BrandDTO brand;
    private Media media;
}
