package com.flame.monitor.admin;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by sungang on 2017/1/6.
 */
@SpringBootApplication
@EnableAdminServer
public class MonitorAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorAdminApplication.class);
    }
}
