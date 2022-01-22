package com.droppynapi.dao;

import com.droppynapi.model.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandDao extends MongoRepository<Brand,String> {
}
