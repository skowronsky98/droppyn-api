package com.droppynapi.dao;

import com.droppynapi.model.SizeChart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeChartDao extends MongoRepository<SizeChart,String> {
}
