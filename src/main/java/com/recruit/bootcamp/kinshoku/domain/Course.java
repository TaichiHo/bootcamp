package com.recruit.bootcamp.kinshoku.domain;

import com.recruit.bootcamp.kinshoku.domain.common.Base;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

/**
 * Created by heyueheng on 5/9/16.
 */
@Document
public class Course extends Base {

    String storeId;

    Map<String, Float> priceMap;

    List<String> ingredients;
}
