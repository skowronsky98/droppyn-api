package com.droppynapi.dto.user;

import com.droppynapi.model.Offer;
import com.droppynapi.model.SizeChart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String id;
    private String username;
    private String email;
    private String firstname;
    private String surname;
    private String phone;
    private String photoURL;
    private String bio;
    private SizeChart defultSize;
//    private List<Offer> myOffers;
//    private List<Offer> favoriteOffers;
}
