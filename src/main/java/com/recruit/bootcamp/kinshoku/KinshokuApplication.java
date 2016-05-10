package com.recruit.bootcamp.kinshoku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class KinshokuApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(KinshokuApplication.class, args);

        RepositoryRestConfiguration repositoryRestConfiguration = ctx.getBean(RepositoryRestConfiguration.class);

        repositoryRestConfiguration.setReturnBodyForPutAndPost(true);
    }
}
