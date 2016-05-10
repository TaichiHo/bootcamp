package com.recruit.bootcamp.kinshoku.domain.common;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by heyueheng on 5/9/16.
 */
@Data
public class Base {

    @Id
    private String id;

    private Date createdAt;
    private Date updatedAt;
}
