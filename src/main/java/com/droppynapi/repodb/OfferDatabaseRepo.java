package com.droppynapi.repodb;

import com.droppynapi.model.Offer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferDatabaseRepo extends MongoRepository<Offer,String> {


}
