package com.droppynapi.model;

import com.droppynapi.exception.ResourceNotFoundException;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;
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
    @DBRef
    private List<Offer> favoriteOffers = new ArrayList<>();

    public User(String _id, String username, String email, String firstname, String surname, String phone, String photoURL, String bio, SizeChart defultSize) {
        this._id = _id;
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.surname = surname;
        this.phone = phone;
        this.photoURL = photoURL;
        this.bio = bio;
        this.defultSize = defultSize;
    }

}
