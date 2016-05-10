package com.recruit.bootcamp.kinshoku.repository;

import com.recruit.bootcamp.kinshoku.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by heyueheng on 5/10/16.
 */
@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends MongoRepository<User, String> {
}
