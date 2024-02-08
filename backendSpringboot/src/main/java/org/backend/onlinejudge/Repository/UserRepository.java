package org.backend.onlinejudge.Repository;

import org.backend.onlinejudge.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {


    Optional<User> findUserByUsername(String username);


    User findByEmail(String username);
}
