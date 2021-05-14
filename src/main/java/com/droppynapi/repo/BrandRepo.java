package com.droppynapi.repo;

import com.droppynapi.model.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepo extends MongoRepository<Brand,String> {
}
