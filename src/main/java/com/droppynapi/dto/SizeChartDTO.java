package com.droppynapi.dto;

import com.droppynapi.model.Brand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SizeChartDTO {
    private String id;
    private String eu;
    private Double us;
    private Double uk;
    private Brand brand;
}
