package com.flame.sleuth.zipkil.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Created by sungang on 2017/1/5.
 */
@SpringBootApplication
@RestController
public class SleuthZipkinDemoAppliaction {


    private static final Logger LOGGER = LoggerFactory.getLogger(SleuthZipkinDemoAppliaction.class);


    public static void main(String[] args) {
        SpringApplication.run(SleuthZipkinDemoAppliaction.class, args);
    }


    @RequestMapping("/")
    public String home() {
        LOGGER.info("Request to / endpoint");
        return "Hello World";
    }


    @RequestMapping("/getTime")
    public String getTime() {
        LOGGER.info("Request to /getTime endpoint");
        return getTimeAsString();
    }

    private String getTimeAsString() {
        LOGGER.info("Returning time as String");
        return LocalDateTime.now().toString();
    }


}
