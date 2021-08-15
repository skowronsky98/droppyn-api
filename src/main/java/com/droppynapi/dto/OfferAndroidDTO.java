package com.droppynapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferAndroidDTO {
    private String _id;
    private Double price;
    private Boolean active;
    private Boolean deleted;

    private String bio;
    private String idShoe;
    private String idSize;
    private String idUser;
}
