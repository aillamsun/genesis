package com.flame.plugin.spring.mybatis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;

/**
 * Created by sungang on 2016/11/16.
 */
//@ConfigurationProperties(prefix = PageHelperProperties.PAGE_HELPER_PREFIX,locations = {"classpath:application.properties","classpath:bootstrap.properties","classpath:application.yml","classpath:bootstrap.yml"})
@ConfigurationProperties(prefix = PageHelperProperties.PAGE_HELPER_PREFIX)
public class PageHelperProperties {

    public static final String PAGE_HELPER_PREFIX = "mybatis.pageHelper";

    private String dialect;
    private String offsetAsPageNum;
    private String rowBoundsWithCount;
    private String pageSizeZero;
    private String params;
    private String supportMethodsArguments;
    private String reasonable;
    private String returnPageInfo;
    private String closeConn;

    @PostConstruct
    public void init() {
        dialect = "mysql";
        offsetAsPageNum = "false";
        rowBoundsWithCount = "false";
        pageSizeZero = "false";
        reasonable = "false";
        params = "pageNum";
        supportMethodsArguments = "false";
        returnPageInfo = "none";
        closeConn = "true";
    }

    public String getOffsetAsPageNum() {
        return offsetAsPageNum;
    }

    public void setOffsetAsPageNum(String offsetAsPageNum) {
        this.offsetAsPageNum = offsetAsPageNum;
    }

    public String getRowBoundsWithCount() {
        return rowBoundsWithCount;
    }

    public void setRowBoundsWithCount(String rowBoundsWithCount) {
        this.rowBoundsWithCount = rowBoundsWithCount;
    }

    public String getReasonable() {
        return reasonable;
    }

    public void setReasonable(String reasonable) {
        this.reasonable = reasonable;
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public String getPageSizeZero() {
        return pageSizeZero;
    }

    public void setPageSizeZero(String pageSizeZero) {
        this.pageSizeZero = pageSizeZero;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getSupportMethodsArguments() {
        return supportMethodsArguments;
    }

    public void setSupportMethodsArguments(String supportMethodsArguments) {
        this.supportMethodsArguments = supportMethodsArguments;
    }

    public String getReturnPageInfo() {
        return returnPageInfo;
    }

    public void setReturnPageInfo(String returnPageInfo) {
        this.returnPageInfo = returnPageInfo;
    }

    public String getCloseConn() {
        return closeConn;
    }

    public void setCloseConn(String closeConn) {
        this.closeConn = closeConn;
    }
}
