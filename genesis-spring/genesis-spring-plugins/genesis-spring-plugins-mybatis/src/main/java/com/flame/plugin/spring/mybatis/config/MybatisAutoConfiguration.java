//package com.flame.plugin.spring.mybatis.config;
//
//import com.github.pagehelper.PageHelper;
//import org.apache.ibatis.mapping.DatabaseIdProvider;
//import org.apache.ibatis.plugin.Interceptor;
//import org.apache.ibatis.session.ExecutorType;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.log4j.Logger;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//import org.springframework.boot.autoconfigure.AutoConfigureBefore;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.DefaultResourceLoader;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.util.Assert;
//import org.springframework.util.ObjectUtils;
//import org.springframework.util.StringUtils;
//
//import javax.annotation.PostConstruct;
//import javax.sql.DataSource;
//import java.util.Properties;
//
///**
// * Created by sungang on 2016/11/16.
// */
//@Configuration
////@ConditionalOnClass({SqlSessionFactory.class, SqlSessionFactoryBean.class})
////@ConditionalOnBean(DataSource.class)
//@EnableConfigurationProperties({MapperProperties.class, PageHelperProperties.class})
//@AutoConfigureBefore(MyBatisMapperScannerConfig.class)
//@AutoConfigureAfter(DataSourceAutoConfiguration.class)
////@Order
//public class MybatisAutoConfiguration {
//
//    private static Logger log = Logger.getLogger(MybatisAutoConfiguration.class);
//
//    @Autowired(required = false)
//    private PageHelperProperties helperProperties;
//
//    @Autowired
//    private MapperProperties properties;
//
//    @Autowired(required = false)
//    private Interceptor[] interceptors;
//
//    @Autowired
//    private ResourceLoader resourceLoader = new DefaultResourceLoader();
//
////    @Autowired(required = false)
////    private DatabaseIdProvider databaseIdProvider;
//
//
//    @PostConstruct
//    public void checkConfigFileExists() {
//        if (this.properties.isCheckConfigLocation()) {
//            Resource resource = this.resourceLoader.getResource(this.properties.getConfigLocation());
//            Assert.state(resource.exists(),
//                    "Cannot find config location: " + resource
//                            + " (please add config file or check your Mybatis " + "configuration)");
//        }
//    }
//
//
//    @Bean(name = "sqlSessionFactory")
//    @ConditionalOnMissingBean
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
//        factory.setDataSource(dataSource);
////        factory.setVfs(SpringBootVFS.class);
//        if (StringUtils.hasText(this.properties.getConfigLocation())) {
//            factory.setConfigLocation(
//                    this.resourceLoader.getResource(this.properties.getConfigLocation()));
//        } else {
//            if (this.interceptors != null && this.interceptors.length > 0) {
//                factory.setPlugins(this.interceptors);
//            }
////            factory.setTypeAliasesPackage(this.mapperProperties.getTypeAliasesPackage());
////            factory.setTypeHandlersPackage(this.mapperProperties.getTypeHandlersPackage());
////            factory.setMapperLocations(this.mapperProperties.getMapperLocations());
//            if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
//                factory.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
//            }
//            if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())) {
//                factory.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
//            }
//            if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
//                factory.setMapperLocations(this.properties.resolveMapperLocations());
//            }
//        }
//        return factory.getObject();
//    }
//
//
//    @Bean
//    @ConditionalOnMissingBean
//    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
//        ExecutorType executorType = this.properties.getExecutorType();
//        if (executorType != null) {
//            return new SqlSessionTemplate(sqlSessionFactory, executorType);
//        } else {
//            return new SqlSessionTemplate(sqlSessionFactory);
//        }
//    }
//
//
//    @Bean
//    @ConditionalOnMissingBean
//    public PageHelper pageHelper(DataSource dataSource) {
//        log.info("注册MyBatis分页插件PageHelper");
//        PageHelper pageHelper = new PageHelper();
//        Properties properties = new Properties();
//        if (null != helperProperties) {
//            properties.setProperty("dialect", helperProperties.getDialect());
//            properties.setProperty("offsetAsPageNum", helperProperties.getOffsetAsPageNum());
//            properties.setProperty("rowBoundsWithCount", helperProperties.getRowBoundsWithCount());
//            properties.setProperty("pageSizeZero", helperProperties.getPageSizeZero());
//            properties.setProperty("reasonable", helperProperties.getReasonable());
//            properties.setProperty("params", helperProperties.getParams());
//            properties.setProperty("supportMethodsArguments", helperProperties.getSupportMethodsArguments());
//            properties.setProperty("returnPageInfo", helperProperties.getReturnPageInfo());
//            properties.setProperty("closeConn", helperProperties.getCloseConn());
////        properties.put("dialect", "mysql");
////        properties.put("reasonable", "true");
////        properties.put("supportMethodsArguments", "true");
////        properties.put("returnPageInfo", "check");
////        properties.put("params", "count=countSql");
////        pageHelper.setProperties(properties);
//        }
//        pageHelper.setProperties(properties);
//
//        return pageHelper;
//    }
//}
