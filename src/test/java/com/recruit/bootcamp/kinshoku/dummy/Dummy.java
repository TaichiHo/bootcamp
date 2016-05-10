package com.recruit.bootcamp.kinshoku.dummy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.recruit.bootcamp.kinshoku.domain.Course;
import com.recruit.bootcamp.kinshoku.domain.Store;
import com.recruit.bootcamp.kinshoku.utils.Constants;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.recruit.bootcamp.kinshoku.TestUtils.BaseIntegrationTest.*;

/**
 * Created by heyueheng on 5/10/16.
 */
public class Dummy {

    public static ResponseEntity<Store> createStore() throws JsonProcessingException {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("storeName", "whateverName");
        requestBody.put("phoneNumber", "3238993908");
        requestBody.put("description", "whatever");
        Map<String, Object> address = new HashMap<>();
        address.put("street", "1302 w23rd street");
        address.put("zip", "90007");
        address.put("city", "Los Angeles");
        Map<String, Object> point = new HashMap<>();
        point.put("type", "Point");
        point.put("coordinates", new float[]{-119.0f, 30.0f});
        address.put("location", point);
        requestBody.put("address", address);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> httpEntity = new HttpEntity<>(OBJECT_MAPPER.writeValueAsString(requestBody), headers);
        ResponseEntity<Store> responseEntity = restTemplate.postForEntity(SERVER_URL + "store",
                httpEntity, Store.class, Collections.emptyMap());
        return responseEntity;
    }

    public static ResponseEntity<Course> createCourse() throws JsonProcessingException {

        Store store = createStore().getBody();
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("storeId", store.getId());
        Map<String, Object> priceMap = new HashMap<>();
        priceMap.put(Constants.MONTH, 300f);
        priceMap.put(Constants.DAY, 20f);
        priceMap.put(Constants.WEEK, 150f);
        requestBody.put("priceMap", priceMap);
        requestBody.put("ingredients", new String[]{"salt", "oil"});
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> httpEntity = new HttpEntity<>(OBJECT_MAPPER.writeValueAsString(requestBody), headers);
        ResponseEntity<Course> responseEntity = restTemplate.postForEntity(SERVER_URL + "course",
                httpEntity, Course.class, Collections.emptyMap());
        return responseEntity;

    }
}
