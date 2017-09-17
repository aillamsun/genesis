package com.flame.tx.user.money.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 启动成功
 * 访问：http://ip:port/druid/login.html即可
 *
 * 注: 如果使用Spring Boot Admin 可能导致监控页面数据源加载失败  原因是因为 Druid 所在项目启动后没有连接到Admin Server服务导致
 * Created by sungang on 2016/12/15.
 */
@Configuration
public class DruidConfig {

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        //reg.addInitParameter("allow", "127.0.0.1"); //IP白名单 (没有配置或者为空，则允许所有访问)
        //reg.addInitParameter("deny",""); //IP黑名单 (存在共同时，deny优先于allow)
        reg.addInitParameter("loginUsername", "admin");
        reg.addInitParameter("loginPassword", "admin");
        reg.addInitParameter("resetEnable", "false");//禁用HTML页面上的“Reset All”功能
        return reg;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*"); //拦截地址
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*"); //不拦截地址
        return filterRegistrationBean;
    }
}
