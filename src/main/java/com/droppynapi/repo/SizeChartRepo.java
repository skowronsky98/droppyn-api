package com.droppynapi.repo;

import com.droppynapi.model.SizeChart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeChartRepo extends MongoRepository<SizeChart,String> {
}
