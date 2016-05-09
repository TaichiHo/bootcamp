package com.recruit.bootcamp.kinshoku.domain.common;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by heyueheng on 5/9/16.
 */

public class Base {
    @Id
    String id;

    Date createdAt;
    Date updatedAt;
}
