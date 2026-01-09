package com.example.V_RGUIDE.repository;

import com.example.V_RGUIDE.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
// We use Generics here: <User, String> means this repo handles User objects with String IDs
public interface UserRepository extends MongoRepository<User, String> {
    // Spring will automatically generate the code to find a user by email!
    User findByEmail(String email);
}