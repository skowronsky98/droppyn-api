package com.droppynapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Media {
    private String imageUrl;
    private String smallImageUrl;
    private String thumbUrl;
}
