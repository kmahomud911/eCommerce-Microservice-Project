package com.shawon.UserService.repository;


import com.shawon.UserService.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);

    @Query(value = "{}", fields = "{'email': 1, 'name': 1}")
    List<User> findAllEmailAndNames();
}
