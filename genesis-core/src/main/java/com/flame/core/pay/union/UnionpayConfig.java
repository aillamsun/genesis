package com.flame.core.pay.union;

import com.flame.core.pay.common.PayConfig;

import java.text.SimpleDateFormat;

public class UnionpayConfig extends PayConfig {

    //编码格式
    public static String encoding = "UTF-8";
    //版本
    public static String version = "5.0.0";
    //商户号
    public static String merId = "777290058110048";
    //后台服务对应的写法参照 FrontRcvResponse.java
    public static String frontUrl = "http://127.0.0.1:8080/cost-api/frontRcvResponse";
    //回调地址
    public static String backUrl = "http://a315678772.oicp.net:25117/geektmall-web/web/pay/unionpayNotify";
    //时间
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
}
