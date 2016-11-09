package com.flame.microservices.zipkin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin.server.EnableZipkinServer;

/**
 * Created by feel on 2016/10/29.
 */
@EnableZipkinServer
@EnableDiscoveryClient
@SpringBootApplication/*(scanBasePackages = {"com.flame"})*/
public class ZipkinServer {


    private static final Logger logger = LoggerFactory.getLogger(ZipkinServer.class);

    public static void main(String[] args) throws Exception {
        new SpringApplicationBuilder(ZipkinServer.class).web(true).run(args);
    }

    //程序首次启动调用
    public void run(String... strings) throws Exception {
        System.out.println("Genesis Server Zipkin Boot Successfully");
    }
}









