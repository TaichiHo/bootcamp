package com.recruit.bootcamp.kinshoku.domain;

import com.recruit.bootcamp.kinshoku.domain.common.Base;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by heyueheng on 5/9/16.
 */
@Data
@Document
public class Transaction extends Base {

    User user;
    Course course;

    String period;
    String paymentPrice;


}
