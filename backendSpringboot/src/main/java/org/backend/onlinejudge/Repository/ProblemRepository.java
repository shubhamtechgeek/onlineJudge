package org.backend.onlinejudge.Repository;

import org.backend.onlinejudge.Entity.Problem;
import org.backend.onlinejudge.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProblemRepository extends MongoRepository<Problem,String> {

    Optional<Problem> findByProblemCode( String code);

    Optional<Problem> findByProblemTitle( String title);

    Optional<List<Problem>> findAllByAuthor(User user);

    void deleteAllByAuthor(User user);
}
