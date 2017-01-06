package com.flame.hystrix.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * Hystrix 在Spring Cloud中使用了Netflix开发的Hystrix来实现熔断器
 *
 * Hystrix监控
 * 除了隔离依赖服务的调用以外，Hystrix还提供了近实时的监控，
 * Hystrix会实时、累加地记录所有关于HystrixCommand的执行信息，
 * 包括每秒执行多少请求多少成功，多少失败等。
 * Netflix通过hystrix-metrics-event-stream项目实现了对以上指标的监控。
 *
 *
 * 启动之后可以访问 服务的地址  如:
 *
 * 访问http://localhost:8030/hystrix.stream
 *
 *
 * Hystrix的监控数据默认是保存在每个实例的内存中的，
 * Spring Boot提供了多种方式，可以导入到Redis、TSDB以供日后分析使用。
 *
 *
 * Created by sungang on 2017/1/6.
 */
@SpringBootApplication
@EnableHystrixDashboard
@Controller
public class HystrixDashboardAppliaction {

    @RequestMapping("/")
    public String home() {
        return "forward:/hystrix";
    }

    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardAppliaction.class, args);
    }
}
