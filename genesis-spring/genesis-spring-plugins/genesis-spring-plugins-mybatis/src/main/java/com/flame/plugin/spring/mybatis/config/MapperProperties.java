package com.flame.plugin.spring.mybatis.config;

import org.apache.ibatis.session.ExecutorType;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sungang on 2016/11/16.
 */
@ConfigurationProperties(prefix = MapperProperties.MAPPER_PREFIX)
public class MapperProperties {

    public static final String MAPPER_PREFIX = "mybatis";

    private String mapperPackage;
    private String mappers;
    private String notEmpty;
    private String identity;
    private String catalog;
    private String order;
    private String schema;
    private String seqFormat;
    private String style;
    private String enableMethodAnnotation;

    /**
     * Config file path.
     */
    private String configLocation;

    /**
     * Location of mybatis mapper files.
     */
    private String[] mapperLocations;

    /**
     * Package to scan domain objects.
     */
    private String typeAliasesPackage;

    /**
     * Package to scan handlers.
     */
    private String typeHandlersPackage;

    /**
     * Check the config file exists.
     */
    private boolean checkConfigLocation = false;

    /**
     * Execution mode.
     */
    private ExecutorType executorType = ExecutorType.SIMPLE;


//    @PostConstruct
//    public void init() {
//        mapperPackage = "";
//        mappers = "tk.mybatis.mapper.common.Mapper";
//        notEmpty = "false";
//        identity = "MYSQL";
//        catalog = "";
//        schema = "";
//        order = "AFTER";
//        seqFormat = "{0}.nextval";
//        style = "camelhump";
//        enableMethodAnnotation = "false";
//    }

    public String getNotEmpty() {
        return notEmpty;
    }

    public void setNotEmpty(String notEmpty) {
        this.notEmpty = notEmpty;
    }

    public String getMappers() {
        return mappers;
    }

    public void setMappers(String mappers) {
        this.mappers = mappers;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getSeqFormat() {
        return seqFormat;
    }

    public void setSeqFormat(String seqFormat) {
        this.seqFormat = seqFormat;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getEnableMethodAnnotation() {
        return enableMethodAnnotation;
    }

    public void setEnableMethodAnnotation(String enableMethodAnnotation) {
        this.enableMethodAnnotation = enableMethodAnnotation;
    }

    public String getMapperPackage() {
        return mapperPackage;
    }

    public void setMapperPackage(String mapperPackage) {
        this.mapperPackage = mapperPackage;
    }

    public String getConfigLocation() {
        return configLocation;
    }

    public void setConfigLocation(String configLocation) {
        this.configLocation = configLocation;
    }

    public String[] getMapperLocations() {
        return mapperLocations;
    }

    public void setMapperLocations(String[] mapperLocations) {
        this.mapperLocations = mapperLocations;
    }

    public String getTypeAliasesPackage() {
        return typeAliasesPackage;
    }

    public void setTypeAliasesPackage(String typeAliasesPackage) {
        this.typeAliasesPackage = typeAliasesPackage;
    }

    public String getTypeHandlersPackage() {
        return typeHandlersPackage;
    }

    public void setTypeHandlersPackage(String typeHandlersPackage) {
        this.typeHandlersPackage = typeHandlersPackage;
    }

    public boolean isCheckConfigLocation() {
        return checkConfigLocation;
    }

    public void setCheckConfigLocation(boolean checkConfigLocation) {
        this.checkConfigLocation = checkConfigLocation;
    }

    public ExecutorType getExecutorType() {
        return executorType;
    }

    public void setExecutorType(ExecutorType executorType) {
        this.executorType = executorType;
    }


    public Resource[] resolveMapperLocations() {
        List<Resource> resources = new ArrayList<Resource>();
        if (this.mapperLocations != null) {
            for (String mapperLocation : this.mapperLocations) {
                Resource[] mappers;
                try {
                    mappers = new PathMatchingResourcePatternResolver().getResources(mapperLocation);
                    resources.addAll(Arrays.asList(mappers));
                } catch (IOException e) {

                }
            }
        }

        Resource[] mapperLocations = new Resource[resources.size()];
        mapperLocations = resources.toArray(mapperLocations);
        return mapperLocations;
    }
}
