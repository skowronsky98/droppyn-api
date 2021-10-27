package com.droppynapi.dao;

import com.droppynapi.model.Offer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferDao extends MongoRepository<Offer,String> {


}
