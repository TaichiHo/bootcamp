package com.recruit.bootcamp.kinshoku.domain;

import com.recruit.bootcamp.kinshoku.domain.common.Address;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

/**
 * Created by heyueheng on 5/9/16.
 */
@Document
public class Store {

    @NotNull
    @Id
    private String id;
    @NotNull
    private String storeName;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String description;

    Address address;

}
