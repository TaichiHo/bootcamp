package com.recruit.bootcamp.kinshoku.repository;

import com.recruit.bootcamp.kinshoku.domain.Store;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by heyueheng on 5/9/16.
 */
@RepositoryRestResource(collectionResourceRel = "store", path = "store")
public interface StoreRepository extends MongoRepository<Store, String> {
}
