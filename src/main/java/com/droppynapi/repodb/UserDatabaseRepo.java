package com.droppynapi.repodb;

import com.droppynapi.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDatabaseRepo extends MongoRepository<User,String> {
}
