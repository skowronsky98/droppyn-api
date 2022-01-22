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
import com.droppynapi.dao.OfferDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService implements OfferRepo {
    @Autowired
    private OfferDao offerDao;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ShoeRepo shoeRepo;

    @Autowired
    private SizeChartRepo sizeChartRepo;

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

        offer = offerDao.save(offer);

        // add reference to user (owner)
        user = assignOfferToUser(user,offer);
        userRepo.saveUser(user);

        return offer;
    }

    @Override
    public List<Offer> getAllOffers(){
        return offerDao.findAll()
                .stream()
                .filter(offer -> !offer.getDeleted() && offer.getActive())
                .collect(Collectors.toList());
    }
    @Override
    public List<Offer> getAllShoeOffers(String shoeId){
        return offerDao.findAll().stream()
                .filter(offer -> offer.getShoe().get_id().equals(shoeId) && !offer.getDeleted() && offer.getActive())
                .collect(Collectors.toList());
    }





//    @Override
//    public Offer addFavoriteOffer(String id, String userId){
//        Offer offer = getOfferById(id);
//        User user = userRepo.getUserById(userId);
//
//
//        user = assignFavoriteOfferToUser(user,offer);
//        userRepo.saveUser(user);
//        return offer;
//    }

    @Override
    public List<Offer> getUserOffers(String userId){
        return userRepo.getUserById(userId).getMyOffers();
    }

//    @Override
//    public List<Offer> getUserFavoriteOffers(String userId){
//        return userRepo.getUserById(userId).getFavoriteOffers();
//    }


    @Override
    public Offer getOfferById(String id){
        return offerDao.findById(id)
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
            user = removeMyOfferFromUser(user,offer);

        userRepo.saveUser(user);

            //set offer to unactice document
            offer.setDeleted(true);
            offerDao.save(offer);
//        }
    }

//    @Override
//    public void removeFavoriteOfferById(String id, String userId){
//        Offer offer = getOfferById(id);
//        User user = userRepo.getUserById(userId);
//        user = removeFavoriteOfferFromUser(user, offer);
//        userRepo.saveUser(user);
//    }

    @Override
    public Offer updateMyOffer(Offer offer,String shoeId, String userId, String sizeId){
        // validate user
//        if(userId.equals(offer.getUser().getId())){
            //remove reference in user document
            User user = userRepo.getUserById(userId);
            // remove old offer | old and bew have same id


            if(offer.get_id() != null)
                user = removeMyOfferFromUser(user,offer);
            else
                System.out.println("!!!!!!ERR");
            offer.setUser(user);
            //add references to offer
            Shoe shoe = shoeRepo.getShoeById(shoeId);
            offer.setShoe(shoe);

            SizeChart sizeChart = sizeChartRepo.getSizeChartById(sizeId);
            offer.setSizeChart(sizeChart);

            offer = offerDao.save(offer);
            // add new offer
            user = assignOfferToUser(user,offer);
            userRepo.saveUser(user);
//
//            return offer;
//        }

        // TODO return error cuz offer wasn't updated
        return offer;
    }

    @Override
    public Page<Offer> getOffersWithPaging(Pageable pageable) {
        return offerDao.findAll(pageable);
    }

    private User assignOfferToUser(User user, Offer offer){
        List<Offer> userOffers = user.getMyOffers();
        userOffers.add(offer);
        user.setMyOffers(userOffers);
        return user;
    }

//    private User assignFavoriteOfferToUser(User user, Offer offer){
//        List<Offer> userOffers = user.getFavoriteOffers();
//        userOffers.add(offer);
//        user.setFavoriteOffers(userOffers);
//        return user;
//    }

    private User removeMyOfferFromUser(User user, Offer offer){

        List<Offer> userOffers = user.getMyOffers();
        Offer originalOffer = userOffers.stream().filter(o -> o.get_id().equals(offer.get_id()))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("no offer with id: "+ offer.get_id()));
        userOffers.remove(originalOffer);
        user.setMyOffers(userOffers);
        return user;
    }

//    private User removeFavoriteOfferFromUser(User user, Offer offer){
//
//        List<Offer> userOffers = user.getFavoriteOffers();
//        Offer originalOffer = userOffers.stream().filter(o -> o.get_id().equals(offer.get_id()))
//                .findFirst()
//                .orElseThrow(() -> new ResourceNotFoundException("no offer with id: "+ offer.get_id()));
//        userOffers.remove(originalOffer);
//        user.setFavoriteOffers(userOffers);
//        return user;
//    }


}
