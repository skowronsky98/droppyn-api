package com.droppynapi.repodb;

import com.droppynapi.model.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandDatabaseRepo extends MongoRepository<Brand,String> {
}
