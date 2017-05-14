//package com.flame.plugin.spring.mybatis.config;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.EnvironmentAware;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import tk.mybatis.spring.mapper.MapperScannerConfigurer;
//
//import javax.annotation.PostConstruct;
//import java.util.Properties;
//
///**
// * Created by sungang on 2016/11/17.
// */
//@Configuration
//// TODO 注意，由于MapperScannerConfigurer执行的比较早，所以必须有下面的注解
//@AutoConfigureAfter(MybatisAutoConfiguration.class)
//@EnableConfigurationProperties({MapperProperties.class,PageHelperProperties.class})
//public class MyBatisMapperScannerConfig implements EnvironmentAware {
//
//    private static Logger log = Logger.getLogger(MyBatisMapperScannerConfig.class);
//
//    @Autowired
//    private MapperProperties properties;
//
//    private Environment environment;
//
//    @PostConstruct
//    public void init() {
//        log.info("MyBatisMapperScannerConfig Init.......");
//    }
//
//    @Bean
//    public MapperScannerConfigurer mapperScannerConfigurer() {
//        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
//        mapperScannerConfigurer.setBasePackage(environment.getProperty("mybatis.mapper-package"));
//        Properties mapperScannerConfigurerProperties = new Properties();
////        if (null != properties) {
//            // 这里要特别注意，不要把 BaseMapper 放到 mapperPackage 中，也就是不能同其他Mapper一样被扫描到。
//        mapperScannerConfigurerProperties.setProperty("mappers", BaseMapper.class.getName());
//        mapperScannerConfigurerProperties.setProperty("notEmpty", "false");
//        mapperScannerConfigurerProperties.setProperty("IDENTITY", "MYSQL");
//
////        mapperScannerConfigurerProperties.setProperty("ORDER", properties.getOrder());
////        mapperScannerConfigurerProperties.setProperty("catalog", properties.getCatalog());
////        mapperScannerConfigurerProperties.setProperty("schema", properties.getSchema());
////        mapperScannerConfigurerProperties.setProperty("seqFormat", properties.getSeqFormat());
////        mapperScannerConfigurerProperties.setProperty("style", properties.getStyle());
////        mapperScannerConfigurerProperties.setProperty("enableMethodAnnotation", properties.getEnableMethodAnnotation());
////        }
//        mapperScannerConfigurer.setProperties(mapperScannerConfigurerProperties);
//
//        return mapperScannerConfigurer;
//    }
//
//    @Override
//    public void setEnvironment(Environment environment) {
//        this.environment = environment;
//
//    }
//}
