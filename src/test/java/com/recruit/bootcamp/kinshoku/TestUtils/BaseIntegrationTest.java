package com.recruit.bootcamp.kinshoku.TestUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.web.client.RestTemplate;

/**
 * Created by heyueheng on 5/10/16.
 */
public class BaseIntegrationTest {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static final RestTemplate restTemplate = new TestRestTemplate();


    private static final int port = 9000;

    public static final String SERVER_URL = "http://localhost:" + port + "/";
}
