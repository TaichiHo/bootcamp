package com.recruit.bootcamp.kinshoku.repository;

import com.recruit.bootcamp.kinshoku.domain.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by heyueheng on 5/10/16.
 */
@RepositoryRestResource(collectionResourceRel = "course", path = "course")
public interface CourseRepository extends MongoRepository<Course, String> {
    List<Course> findByStoreId(@Param("storeId") String storeId);
}
