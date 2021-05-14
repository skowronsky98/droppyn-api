package com.droppynapi.dto;

import com.droppynapi.model.Shoe;
import com.droppynapi.model.SizeChart;
import com.droppynapi.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferDTO {
    private String id;
    private Double price;
    private Boolean active;
    private String bio;
    private Shoe shoe;
    private SizeChart sizeChart;
    private User user;
}
