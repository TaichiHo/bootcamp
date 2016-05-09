package com.recruit.bootcamp.kinshoku.domain;

import com.recruit.bootcamp.kinshoku.domain.common.Address;
import com.recruit.bootcamp.kinshoku.domain.common.Base;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by heyueheng on 5/9/16.
 */
@Document
public class User extends Base {

    String firstName;
    String lastName;
    String email;
    String password;

    // for stripe
    String paymentToken;

    Address address;
}
