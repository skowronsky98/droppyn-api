package com.droppynapi.dao;

import com.droppynapi.model.Offer;
import com.droppynapi.model.Shoe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferDao extends MongoRepository<Offer,String> {

//    Page<Offer> getOffersWithPaging(Pageable pageable);
//    Page<Offer> getOfferByShoeWithPaging(Shoe shoe, Pageable pageable);

}
