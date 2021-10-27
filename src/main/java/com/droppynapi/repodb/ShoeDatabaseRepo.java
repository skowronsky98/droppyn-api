package com.droppynapi.repodb;

import com.droppynapi.model.Shoe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoeDatabaseRepo extends MongoRepository<Shoe,String> {
}
