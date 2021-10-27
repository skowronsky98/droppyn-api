package com.droppynapi.service;

import com.droppynapi.exception.ResourceNotFoundException;
import com.droppynapi.model.Offer;
import com.droppynapi.model.Shoe;
import com.droppynapi.model.SizeChart;
import com.droppynapi.model.User;
import com.droppynapi.repo.OfferRepo;
import com.droppynapi.repo.ShoeRepo;
import com.droppynapi.repo.SizeChartRepo;
import com.droppynapi.repo.UserRepo;
import com.droppynapi.repodb.OfferDatabaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService implements OfferRepo {
    @Autowired
    private OfferDatabaseRepo offerDatabaseRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ShoeRepo shoeRepo;

    @Autowired
    private SizeChartRepo sizeChartRepo;

    @Override
    public List<Offer> getAllOffers(){
        return offerDatabaseRepo.findAll()
                .stream()
                .filter(offer -> !offer.getDeleted() && offer.getActive())
                .collect(Collectors.toList());
    }
    @Override
    public List<Offer> getAllShoeOffers(String shoeId){
        return offerDatabaseRepo.findAll().stream()
                .filter(offer -> offer.getShoe().get_id().equals(shoeId) && !offer.getDeleted() && offer.getActive())
                .collect(Collectors.toList());
    }

    @Override
    public Offer addOffer(Offer offer,String shoeId, String userId, String sizeId){
        //add references to offer
        offer.set_id(null);
        Shoe shoe = shoeRepo.getShoeById(shoeId);
        offer.setShoe(shoe);

        SizeChart sizeChart = sizeChartRepo.getSizeChartById(sizeId);
        offer.setSizeChart(sizeChart);

        User user = userRepo.getUserById(userId);
        offer.setUser(user);

        offer = offerDatabaseRepo.save(offer);

        // add reference to user (owner)

        user.addOffer(offer);
        userRepo.createUser(user);

        return offer;
    }

    @Override
    public Offer addFavoriteOffer(String id, String userId){
        Offer offer = getOfferById(id);
        User user = userRepo.getUserById(userId);
        user.addFavoriteOffer(offer);
        userRepo.createUser(user);
        return offer;
    }

    @Override
    public List<Offer> getUserOffers(String userId){
        return userRepo.getUserById(userId).getMyOffers();
    }

    @Override
    public List<Offer> getUserFavoriteOffers(String userId){
        return userRepo.getUserById(userId).getFavoriteOffers();
    }


    @Override
    public Offer getOfferById(String id){
        return offerDatabaseRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no offer with id: "+id));
    }

    //TODO remove refrence to Favorite Offers field in User
    @Override
    public void removeMyOfferById(String id, String userId){
        Offer offer = getOfferById(id);

        // validate user
//        if(userId.equals(offer.getUser().getId())){
            //remove reference in user document
            User user = userRepo.getUserById(userId);
            user.removeMyOffer(id);
            userRepo.createUser(user);

            //set offer to unactice document
            offer.setDeleted(true);
            offerDatabaseRepo.save(offer);
//        }
    }

    @Override
    public void removeFavoriteOfferById(String id, String userId){
//        Offer offer = getOfferById(id);
        User user = userRepo.getUserById(userId);
        user.removeFavoriteOffer(id);
        userRepo.createUser(user);
    }

    @Override
    public Offer updateMyOffer(Offer offer,String shoeId, String userId, String sizeId){
        // validate user
//        if(userId.equals(offer.getUser().getId())){
            //remove reference in user document
            User user = userRepo.getUserById(userId);
            // remove old offer | old and bew have same id


            if(offer.get_id() != null)
                user.removeMyOffer(offer.get_id());
            else
                System.out.println("!!!!!!ERR");
            offer.setUser(user);
            //add references to offer
            Shoe shoe = shoeRepo.getShoeById(shoeId);
            offer.setShoe(shoe);

            SizeChart sizeChart = sizeChartRepo.getSizeChartById(sizeId);
            offer.setSizeChart(sizeChart);

            offer = offerDatabaseRepo.save(offer);
            // add new offer
            user.addOffer(offer);
            userRepo.createUser(user);
//
//            return offer;
//        }

        // TODO return error cuz offer wasn't updated
        return offer;
    }

}
