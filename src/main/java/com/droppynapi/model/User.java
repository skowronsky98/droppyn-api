package com.droppynapi.model;

import com.droppynapi.exception.ResourceNotFoundException;
import lombok.*;
import org.springframework.data.annotation.Id;
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
    private String username;
    private String password;
    private String email;
    private String firstname;
    private String surname;
    private String phone;
    private String photoURL;
    private String bio;
    private SizeChart defultSize;

    @DBRef
    private List<Offer> myOffers;
    @DBRef
    private List<Offer> favoriteOffers;

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

    public Offer addOffer(Offer offer){
        if(myOffers == null)
            myOffers = new ArrayList<>();

        myOffers.add(offer);
        return offer;
    }

    public boolean removeMyOffer(String idOffer){
        if(myOffers==null)
            return false;
        Offer offer = myOffers.stream().filter(o -> o.get_id().equals(idOffer))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("no offer with id: "+idOffer));
        return myOffers.remove(offer);
    }

    public Offer addFavoriteOffer(Offer offer){
        if(favoriteOffers == null)
            favoriteOffers = new ArrayList<>();
        favoriteOffers.add(offer);
        return offer;
    }

    public boolean removeFavoriteOffer(String idOffer){
        if(favoriteOffers==null)
            return false;

        Offer offer = favoriteOffers.stream().filter(o -> o.get_id().equals(idOffer))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("no brand with id: "+idOffer));
        return favoriteOffers.remove(offer);
    }

    public List<Offer> getMyOffers() {
        if(myOffers != null)
            return myOffers;
        return new ArrayList<>();
    }

    public List<Offer> getFavoriteOffers() {
        if(favoriteOffers!=null)
            return favoriteOffers;
        return new ArrayList<>();
    }
}
