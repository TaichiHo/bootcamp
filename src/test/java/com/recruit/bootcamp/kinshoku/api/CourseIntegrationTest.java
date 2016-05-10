package com.recruit.bootcamp.kinshoku.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.recruit.bootcamp.kinshoku.KinshokuApplication;
import com.recruit.bootcamp.kinshoku.TestUtils.BaseIntegrationTest;
import com.recruit.bootcamp.kinshoku.domain.Course;
import com.recruit.bootcamp.kinshoku.domain.Store;
import com.recruit.bootcamp.kinshoku.repository.CourseRepository;
import com.recruit.bootcamp.kinshoku.repository.StoreRepository;
import com.recruit.bootcamp.kinshoku.utils.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.recruit.bootcamp.kinshoku.dummy.Dummy.createCourse;
import static com.recruit.bootcamp.kinshoku.dummy.Dummy.createStore;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Created by heyueheng on 5/10/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = KinshokuApplication.class)
@WebIntegrationTest("server.port:9000")
public class CourseIntegrationTest extends BaseIntegrationTest {

    @Autowired
    StoreRepository storeRepository;
    @Autowired
    CourseRepository courseRepository;

    @Test
    public void testCreateCourseApi() throws JsonProcessingException {
        ResponseEntity<Course> responseEntity = createCourse();
        Course course = responseEntity.getBody();
        assertNotNull(course);
        assertNotNull(course.getId());


        Course courseFromDB = courseRepository.findOne(course.getId());
        assertEquals(course.getIngredients(), courseFromDB.getIngredients());
        assertEquals(course.getPriceMap(), courseFromDB.getPriceMap());

        storeRepository.delete(course.getStoreId());
        courseRepository.delete(course.getId());

    }

    @Test
    public void testCreateInvalidCourseWillFail() throws JsonProcessingException {
        /**
         * Fail on missing price map
         */
        Store store = createStore().getBody();
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("storeId", store.getId());
        requestBody.put("ingredients", new String[]{"salt", "oil"});
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> httpEntity = new HttpEntity<>(OBJECT_MAPPER.writeValueAsString(requestBody), headers);
        ResponseEntity<Course> responseEntity = restTemplate.postForEntity(SERVER_URL + "course",
                httpEntity, Course.class, Collections.emptyMap());

        assertEquals(BAD_REQUEST, responseEntity.getStatusCode());

        /**
         * Fail on invalid price map
         */
        requestBody = new HashMap<>();
        requestBody.put("storeId", store.getId());
        Map<String, Object> priceMap = new HashMap<>();
        priceMap.put(Constants.MONTH, 300f);
        priceMap.put(Constants.DAY, 20f);
        // invalid price map
        priceMap.put(Constants.WEEK, "invalid price");
        requestBody.put("priceMap", priceMap);
        requestBody.put("ingredients", new String[]{"salt", "oil"});

        httpEntity = new HttpEntity<>(OBJECT_MAPPER.writeValueAsString(requestBody), headers);
        responseEntity = restTemplate.postForEntity(SERVER_URL + "course",
                httpEntity, Course.class, Collections.emptyMap());

        assertEquals(BAD_REQUEST, responseEntity.getStatusCode());

        /**
         * Fail on missing store id
         */
        requestBody = new HashMap<>();
        priceMap = new HashMap<>();
        priceMap.put(Constants.MONTH, 300f);
        priceMap.put(Constants.DAY, 20f);
        priceMap.put(Constants.WEEK, 100f);
        requestBody.put("priceMap", priceMap);
        requestBody.put("ingredients", new String[]{"salt", "oil"});

        httpEntity = new HttpEntity<>(OBJECT_MAPPER.writeValueAsString(requestBody), headers);
        responseEntity = restTemplate.postForEntity(SERVER_URL + "course",
                httpEntity, Course.class, Collections.emptyMap());

        assertEquals(BAD_REQUEST, responseEntity.getStatusCode());
    }

}
