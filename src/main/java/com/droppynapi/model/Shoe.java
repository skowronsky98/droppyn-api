package com.droppynapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "shoe")
public class Shoe {
    @Id
    private String _id;
    private String model;

    @DBRef
    private Brand brand;
    private Media media;
    private Boolean favorite;

}
