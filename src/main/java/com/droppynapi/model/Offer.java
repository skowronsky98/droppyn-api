package com.droppynapi.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "offer")
public class Offer {
    @Id
    private String _id;
    private Double price;
    @Field
    private Boolean active = true;
    private Boolean deleted;
    private String bio;

    @DBRef
    private Shoe shoe;
    @DBRef
    private SizeChart sizeChart;
    @DBRef
    private User user;

}
