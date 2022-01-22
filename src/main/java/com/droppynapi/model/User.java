package com.droppynapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.LazyBSONList;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
public class User {
    @Id
    private String _id;
    @Indexed(unique = true)
    private String username;
    @Indexed(unique = true)
    private String email;
    private String password;
    private String firstname;
    private String surname;
    private String phone;
    private String photoURL;
    private String bio;
    private SizeChart defultSize;

    @DBRef
    private List<Offer> myOffers = new ArrayList<>();
//    @DBRef
//    private List<Offer> favoriteOffers = new ArrayList<>();
    @DBRef
    private List<Role> roles = new ArrayList<>();
}
