package com.droppynapi.service;

import com.droppynapi.exception.ResourceNotFoundException;
import com.droppynapi.model.Offer;
import com.droppynapi.model.Shoe;
import com.droppynapi.model.SizeChart;
import com.droppynapi.model.User;
import com.droppynapi.repo.OfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class OfferService {
    @Autowired
    private OfferRepo offerRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private ShoeService shoeService;

    @Autowired
    private SizeChartService sizeChartService;

    public Collection<Offer> getAllOffers(){
        return offerRepo.findAll();
    }

    public Offer addOffer(Offer offer,String shoeId, String userId, String sizeId){
        // add reference to user (owner)
        User user = userService.getUserById(userId);
        user.addOffer(offer);
        userService.createUser(user);

        //add references to offer
        Shoe shoe = shoeService.getShoeById(shoeId);
        offer.setShoe(shoe);

        SizeChart sizeChart = sizeChartService.getSizeChartById(sizeId);
        offer.setSizeChart(sizeChart);

        return offerRepo.save(offer);
    }

    public Offer addFavoriteOffer(String id, String userId){
        Offer offer = getOfferById(id);
        User user = userService.getUserById(userId);
        user.addFavoriteOffer(offer);
        userService.createUser(user);
        return offer;
    }

    public Collection<Offer> getUserOffers(String userId){
        return userService.getUserById(userId).getMyOffers();
    }

    public Collection<Offer> getUserFavoriteOffers(String userId){
        return userService.getUserById(userId).getFavoriteOffers();
    }

    public Offer getOfferById(String id){
        return offerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no offer with id: "+id));
    }

    //TODO remove refrence to Favorite Offers field in User
    public void removeMyOfferById(String id, String userId){
        Offer offer = getOfferById(id);

        // validate user
        if(userId.equals(offer.getUser().getId())){
            //remove reference in user document
            User user = userService.getUserById(userId);
            user.removeMyOffer(id);
            userService.createUser(user);

            //set offer to unactice document
            offer.setActive(false);
            offerRepo.save(offer);
        }
    }

    public void removeFavoriteOfferById(String id, String userId){
        Offer offer = getOfferById(id);
        User user = userService.getUserById(userId);
        user.removeFavoriteOffer(id);
        userService.createUser(user);
    }

    public Offer updateMyOffer(Offer offer,String shoeId, String userId, String sizeId){
        // validate user
        if(userId.equals(offer.getUser().getId())){
            //remove reference in user document
            User user = userService.getUserById(userId);
            // remove old offer | old and bew have same id
            user.removeMyOffer(offer.get_id());

            //add references to offer
            Shoe shoe = shoeService.getShoeById(shoeId);
            offer.setShoe(shoe);

            SizeChart sizeChart = sizeChartService.getSizeChartById(sizeId);
            offer.setSizeChart(sizeChart);

            // add new offer
            user.addOffer(offer);
            userService.createUser(user);

            return offerRepo.save(offer);
        }

        // TODO return error cuz offer wasn't updated
        return offer;
    }

}
