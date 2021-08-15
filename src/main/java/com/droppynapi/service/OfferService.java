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
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Offer> getAllOffers(){
        return offerRepo.findAll()
                .stream()
                .filter(offer -> !offer.getDeleted() && offer.getActive())
                .collect(Collectors.toList());
    }

    public List<Offer> getAllShoeOffers(String shoeId){
        return offerRepo.findAll().stream()
                .filter(offer -> offer.getShoe().get_id().equals(shoeId) && !offer.getDeleted() && offer.getActive())
                .collect(Collectors.toList());
    }

    public Offer addOffer(Offer offer,String shoeId, String userId, String sizeId){
        //add references to offer

        Shoe shoe = shoeService.getShoeById(shoeId);
        offer.setShoe(shoe);

        SizeChart sizeChart = sizeChartService.getSizeChartById(sizeId);
        offer.setSizeChart(sizeChart);

        User user = userService.getUserById(userId);
        offer.setUser(user);

        offer = offerRepo.save(offer);

        // add reference to user (owner)

        user.addOffer(offer);
        userService.createUser(user);

        return offer;
    }

    public Offer addFavoriteOffer(String id, String userId){
        Offer offer = getOfferById(id);
        User user = userService.getUserById(userId);
        user.addFavoriteOffer(offer);
        userService.createUser(user);
        return offer;
    }

    public List<Offer> getUserOffers(String userId){
        return userService.getUserById(userId).getMyOffers();
    }

    public List<Offer> getUserFavoriteOffers(String userId){
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
//        if(userId.equals(offer.getUser().getId())){
            //remove reference in user document
            User user = userService.getUserById(userId);
            user.removeMyOffer(id);
            userService.createUser(user);

            //set offer to unactice document
            offer.setDeleted(true);
            offerRepo.save(offer);
//        }
    }

    public void removeFavoriteOfferById(String id, String userId){
//        Offer offer = getOfferById(id);
        User user = userService.getUserById(userId);
        user.removeFavoriteOffer(id);
        userService.createUser(user);
    }

    public Offer updateMyOffer(Offer offer,String shoeId, String userId, String sizeId){
        // validate user
//        if(userId.equals(offer.getUser().getId())){
            //remove reference in user document
            User user = userService.getUserById(userId);
            // remove old offer | old and bew have same id


            if(offer.get_id() != null)
                user.removeMyOffer(offer.get_id());
            else
                System.out.println("!!!!!!ERR");
            offer.setUser(user);
            //add references to offer
            Shoe shoe = shoeService.getShoeById(shoeId);
            offer.setShoe(shoe);

            SizeChart sizeChart = sizeChartService.getSizeChartById(sizeId);
            offer.setSizeChart(sizeChart);

            offer = offerRepo.save(offer);
            // add new offer
            user.addOffer(offer);
            userService.createUser(user);
//
//            return offer;
//        }

        // TODO return error cuz offer wasn't updated
        return offer;
    }

}
