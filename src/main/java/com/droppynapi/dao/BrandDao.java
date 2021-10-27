package com.droppynapi.dao;

import com.droppynapi.model.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandDao extends MongoRepository<Brand,String> {
}
