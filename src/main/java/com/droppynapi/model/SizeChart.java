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
@Document(collection = "sizechart")
public class SizeChart {
    @Id
    private String _id;
    private String eu;
    private Double us;
    private Double uk;
    private String type;
    @DBRef
    private Brand brand;

}
