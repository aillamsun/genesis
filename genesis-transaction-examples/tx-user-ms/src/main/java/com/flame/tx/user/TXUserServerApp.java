package com.flame.tx.user;

import com.alibaba.druid.pool.DruidDataSource;
import com.lorne.tx.compensate.repository.CompensateDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * Created by sungang on 2017/9/16.
 */

@EnableAutoConfiguration
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@MapperScan("com.flame.mapper")
public class TXUserServerApp {

    public static void main(String[] args) {
        SpringApplication.run(TXUserServerApp.class, args);
    }


    @Autowired
    private Environment env;


    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;



    @Bean
    public CompensateDataSource compensateDataSource() {

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(username);//用户名
        dataSource.setPassword(password);//密码
        dataSource.setInitialSize(1);
        dataSource.setMaxActive(5);
        dataSource.setMinIdle(0);
        dataSource.setMaxWait(60000);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestOnBorrow(false);
        dataSource.setTestWhileIdle(true);
        dataSource.setPoolPreparedStatements(false);

        CompensateDataSource compensateDataSource = new CompensateDataSource();
        compensateDataSource.setDataSource(dataSource);
        return compensateDataSource;
    }

}
