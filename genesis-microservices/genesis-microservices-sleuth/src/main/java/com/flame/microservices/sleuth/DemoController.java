package com.flame.microservices.sleuth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by sungang on 2017/1/5.
 */
@RestController
public class DemoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private Tracer tracer;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DateService dateService;

    @Autowired
    private LogService logService;

    @Value("${server.port}")
    private int appPort;

    @Value("${otherapp.port}")
    private int otherappPort;


    @RequestMapping("/")
    public String home() {
        LOGGER.info("Request to / endpoint");
        return "Hello World";
    }

    @RequestMapping("/getRemoteTime")
    public String getRemoteTime() throws URISyntaxException {
        LOGGER.info("Request to /getRemoteTime endpoint");
        logService.log("Will call remote service");
        ResponseEntity<String> forEntity = restTemplate.getForEntity(new URI("http://localhost:" + otherappPort + "/getTime"), String.class);
        LOGGER.info("Got response code: {}", forEntity.getStatusCode().toString());
        return "The remote time is: " + forEntity.getBody();
    }

    @RequestMapping("/getLocalTime")
    public String getLocalTime() throws URISyntaxException {
        LOGGER.info("Request to /getLocalTime endpoint");
        logService.log("Will call local service");
        ResponseEntity<String> forEntity = restTemplate.getForEntity(new URI("http://localhost:" + appPort + "/getTime"), String.class);
        LOGGER.info("Got response code: {}", forEntity.getStatusCode().toString());
        return "The localtime is: " + forEntity.getBody();
    }

    @RequestMapping("/getTime")
    public String getTime() {
        LOGGER.info("Request to /getTime endpoint");
        return dateService.getTimeAsString();
    }
}
