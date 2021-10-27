package com.droppynapi.repodb;

import com.droppynapi.model.SizeChart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeChartDatabaseRepo extends MongoRepository<SizeChart,String> {
}
