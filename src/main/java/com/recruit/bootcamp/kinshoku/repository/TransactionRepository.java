package com.recruit.bootcamp.kinshoku.repository;

import com.recruit.bootcamp.kinshoku.domain.Course;
import com.recruit.bootcamp.kinshoku.domain.Transaction;
import com.recruit.bootcamp.kinshoku.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by heyueheng on 5/10/16.
 */
@RepositoryRestResource(collectionResourceRel = "transaction", path = "transaction")
public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findByUser(@Param("user") User user);

    List<Transaction> findByCourse(@Param("course") Course course);
}
