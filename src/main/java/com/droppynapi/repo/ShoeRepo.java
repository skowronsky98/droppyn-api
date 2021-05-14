package com.droppynapi.repo;

import com.droppynapi.model.Shoe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoeRepo extends MongoRepository<Shoe,String> {
}
