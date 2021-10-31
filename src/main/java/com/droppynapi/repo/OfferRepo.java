package com.droppynapi.repo;

import com.droppynapi.model.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface OfferRepo {
    Collection<Offer> getAllOffers();
    Collection<Offer> getAllShoeOffers(String shoeId);
    Offer addOffer(Offer offer,String shoeId, String userId, String sizeId);
    Offer addFavoriteOffer(String id, String userId);
    Collection<Offer> getUserOffers(String userId);
    Collection<Offer> getUserFavoriteOffers(String userId);
    Offer getOfferById(String id);
    void removeMyOfferById(String id, String userId);
    void removeFavoriteOfferById(String id, String userId);
    Offer updateMyOffer(Offer offer,String shoeId, String userId, String sizeId);

    Page<Offer> getOffersWithPaging(Pageable pageable);



}
