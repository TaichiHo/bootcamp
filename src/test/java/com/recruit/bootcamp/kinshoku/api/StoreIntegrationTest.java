package com.recruit.bootcamp.kinshoku.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.recruit.bootcamp.kinshoku.KinshokuApplication;
import com.recruit.bootcamp.kinshoku.TestUtils.BaseIntegrationTest;
import com.recruit.bootcamp.kinshoku.TestUtils.Utils;
import com.recruit.bootcamp.kinshoku.domain.Store;
import com.recruit.bootcamp.kinshoku.repository.StoreRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.recruit.bootcamp.kinshoku.dummy.Dummy.createStore;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Integration tests. Should write the unit tests I think
 * Created by heyueheng on 5/10/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = KinshokuApplication.class)
@WebIntegrationTest("server.port:9000")
public class StoreIntegrationTest extends BaseIntegrationTest {
    // use the delete the data added for tests
    @Autowired
    private StoreRepository storeRepository;


    @Before
    public void setup() {
        Utils.reviseRestTemplate(restTemplate);
    }


    @Test
    public void testCreateStoreApi() throws JsonProcessingException {
        ResponseEntity<Store> responseEntity = createStore();

        Store store = responseEntity.getBody();
        System.out.println(store);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);

        assertNotNull(store.getId());
        Store storeFromDB = storeRepository.findOne(store.getId());

        assertNotNull(store);
        assertEquals(store.getAddress(), storeFromDB.getAddress());
        assertEquals(store.getDescription(), storeFromDB.getDescription());
        assertEquals(store.getPhoneNumber(), storeFromDB.getPhoneNumber());
        assertEquals(store.getPhoneNumber(), "3238993908");
        assertEquals("Los Angeles", store.getAddress().getCity());
        assertEquals("90007", storeFromDB.getAddress().getZip());

        storeRepository.delete(store.getId());
    }

    @Test
    public void testCreatesStoreApiFail() throws JsonProcessingException {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("storeName", "whateverName");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(OBJECT_MAPPER.writeValueAsString(requestBody), headers);
        ResponseEntity<Store> responseEntity = restTemplate.postForEntity(SERVER_URL + "store",
                httpEntity, Store.class, Collections.emptyMap());
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }



}

