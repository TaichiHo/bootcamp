package com.recruit.bootcamp.kinshoku.config;

import com.recruit.bootcamp.kinshoku.domain.Course;
import com.recruit.bootcamp.kinshoku.domain.Store;
import com.recruit.bootcamp.kinshoku.domain.Transaction;
import com.recruit.bootcamp.kinshoku.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Created by heyueheng on 5/10/16.
 */
@Configuration
public class RepositoryConfiguration extends RepositoryRestConfigurerAdapter {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
//        System.out.println("ExposeId called");
        super.configureRepositoryRestConfiguration(config);
        config.exposeIdsFor(Store.class);
        config.exposeIdsFor(Transaction.class);
        config.exposeIdsFor(Course.class);
        config.exposeIdsFor(User.class);
    }

    @Bean
    @Primary
    /**
     * Create a validator to use in bean validation - primary to be able to autowire without qualifier
     */
    Validator validator() {
        return new LocalValidatorFactoryBean();
    }

//    @Bean
//    //the bean name starting with beforeCreate will result into registering the validator before insert
//    public BeforeCreateCompanyValidator beforeCreateCompanyValidator() {
//        return new BeforeCreateCompanyValidator();
//    }

    @Override
    public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
        Validator validator = validator();
        //bean validation always before save and create
        validatingListener.addValidator("beforeCreate", validator);
        validatingListener.addValidator("beforeSave", validator);
    }
}
