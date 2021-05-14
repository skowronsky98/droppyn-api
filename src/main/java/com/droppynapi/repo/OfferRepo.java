package com.droppynapi.repo;

import com.droppynapi.model.Offer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepo extends MongoRepository<Offer,String> {
}
