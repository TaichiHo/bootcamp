package com.recruit.bootcamp.kinshoku.domain;

import com.recruit.bootcamp.kinshoku.domain.common.Address;
import com.recruit.bootcamp.kinshoku.domain.common.Base;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;


/**
 * Created by heyueheng on 5/9/16.
 */
@Data
@Document
public class Store extends Base {

    @NotNull
    private String storeName;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String description;

    @NotNull
    private Address address;


    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
