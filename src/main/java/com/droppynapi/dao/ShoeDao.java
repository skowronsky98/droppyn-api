package com.droppynapi.dao;

import com.droppynapi.model.Shoe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoeDao extends MongoRepository<Shoe,String> {
}
