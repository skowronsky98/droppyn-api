package com.droppynapi.dao;

import com.droppynapi.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends MongoRepository<Role,String> {
    Role findRoleByName(String name);
}
