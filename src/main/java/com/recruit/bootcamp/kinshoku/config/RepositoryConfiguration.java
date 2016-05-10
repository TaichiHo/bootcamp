package com.recruit.bootcamp.kinshoku.config;

import com.recruit.bootcamp.kinshoku.domain.Course;
import com.recruit.bootcamp.kinshoku.domain.Transaction;
import com.recruit.bootcamp.kinshoku.domain.User;
import org.apache.catalina.Store;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

/**
 * Created by heyueheng on 5/10/16.
 */
@Configuration
public class RepositoryConfiguration extends RepositoryRestConfigurerAdapter {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        super.configureRepositoryRestConfiguration(config);
        config.exposeIdsFor(Store.class);
        config.exposeIdsFor(Transaction.class);
        config.exposeIdsFor(Course.class);
        config.exposeIdsFor(User.class);
    }
}
