package com.flame.server.zipkin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin.server.EnableZipkinServer;

/**
 * Created by feel on 2016/10/29.
 */
@EnableZipkinServer
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.flame"})
public class ZipkinServer {


    private static final Logger logger = LoggerFactory.getLogger(ZipkinServer.class);

    public static void main(String[] args) throws Exception {
        //开启监控 http://localhost:8080/health
        SpringApplication.run(ZipkinServer.class, args);

    }
}









