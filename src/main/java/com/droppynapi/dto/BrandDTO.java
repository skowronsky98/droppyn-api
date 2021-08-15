package com.droppynapi.dto;


import com.droppynapi.model.Media;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandDTO {
    private String _id;
    private String name;
    private Media media;
}
