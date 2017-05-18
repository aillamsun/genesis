package com.flame.provider.account.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by sungang on 2017/5/18.
 */
@SpringBootApplication
public class ProviderAccountMsApp extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ProviderAccountMsApp.class);
    }


    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ProviderAccountMsApp.class);

        app.run(args);
    }
}
