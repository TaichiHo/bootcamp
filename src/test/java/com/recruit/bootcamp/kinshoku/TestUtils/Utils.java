package com.recruit.bootcamp.kinshoku.TestUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.geo.GeoModule;
import org.springframework.data.mongodb.core.geo.GeoJsonModule;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by heyueheng on 5/10/16.
 */
public class Utils {

    public static RestTemplate reviseRestTemplate(RestTemplate restTemplate) {
        for (int i = 0; i < restTemplate.getMessageConverters().size(); i++) {
            final HttpMessageConverter<?> httpMessageConverter = restTemplate.getMessageConverters().get(i);
            if (httpMessageConverter instanceof MappingJackson2HttpMessageConverter) {
                restTemplate.getMessageConverters().set(i, mappingJackson2HttpMessageConverter());
            }
        }
        return restTemplate;
    }

    public static RestTemplate restTemplate() {
        final RestTemplate restTemplate = new RestTemplate();

        //find and replace Jackson message converter with our own
        for (int i = 0; i < restTemplate.getMessageConverters().size(); i++) {
            final HttpMessageConverter<?> httpMessageConverter = restTemplate.getMessageConverters().get(i);
            if (httpMessageConverter instanceof MappingJackson2HttpMessageConverter) {
                restTemplate.getMessageConverters().set(i, mappingJackson2HttpMessageConverter());
            }
        }

        return restTemplate;
    }

    public static MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(MediaType.parseMediaTypes("application/hal+json"));

        converter.setObjectMapper(myObjectMapper());
        return converter;
    }

    public static ObjectMapper myObjectMapper() {
        // return your own object mapper

        ObjectMapper OBJECT_MAPPER = new ObjectMapper();
        // register the geojsonmodule to avoid the constructor bug
        OBJECT_MAPPER.registerModule(new GeoJsonModule());
        OBJECT_MAPPER.registerModule(new GeoModule());

        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.registerModule(new Jackson2HalModule());
        return OBJECT_MAPPER;
    }


}
