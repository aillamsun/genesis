package com.flame.core.pay.alipay;


import com.flame.core.pay.common.PayConfig;

public class AlipayConfig extends PayConfig {

    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    // 合作身份者ID，以2088开头由16位纯数字组成的字符串
    //查看地址：https://b.alipay.com/order/pidAndKey.htm
    public static String partner = "2088021950995910";

    // 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
    public static String seller_id = partner;

    // 收款支付宝账号
    public static String seller_email = "";


    // 商户的私钥
    public static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAN9XMkY3UvL0SRCrodDGwJ0dCvIux8/R0ulbTV3OscMJHNkvZgvmZOCkDv1SWfhktg/oJKc5vBPtxO41vVqBEVdFTcVLKOciU66+JqlTtc5f+8k83NGzJNkX+HD4FuUAKLbuLdFKldlvwKj0EyuGUa4dkDZo0z+FuemaNQsKdZDZAgMBAAECgYEAqEGLuYRidHB/PxTecmfZokPIBE4j8DfWGkHTllFqcFHELwbbbSmkY+yU8lmBOHW/B1rPikAfpi/hxcgsoCtOQLVSKIq2XV7CM83sEbEnINS3m3xHMVYp20LAiW0AsjNMegGd/oAtawOknxPX4OVcqg+rTA9SLnvU0HmqeO42VJECQQD7g5hUcEGhZ8VZLsLX3CQDAlQpICWTs4oZMRR8xjnox89E9AeNRpt89f4YYd4JOopOBSFTVrS4mJ8WBzzLK2hdAkEA41L2Xbzcir0ubquRhq1xSYs9AcMf4GWejQ/bdxlb0UyqjMaI6QkFMAtQ0WE7VapwXaW0Iutsw6/DcwGkSVeSrQJATzUHIPhlQs5qUQav58j1MllmZbkw/E3bXMmfGRhYHl3mgWGUi0JEL1tM1SLlCOT9r0pgT12L9mJiH07SL22RJQJAOXmuZMDtmf5/EOg8ISsVK50SzbFCDABleBD8Uj6NInwZ2wIPd6waLa2Ov4Gv+NbhFixhQTr5Avkbl6ZgnVKQ+QJBAKE1tDy3mQrxQEh3964sNWLFzcZkJTZKSLteo8SfRQm9tykGDGpPd5jtTC8kZUNhooEEDhQoGiVUCs7UpO3vIco=";

    // 支付宝的公钥，无需修改该值
    public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
    //↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑


    // 调试用，创建TXT日志文件夹路径
    public static String log_path = "D:\\";

    // 字符编码格式 目前支持 gbk 或 utf-8
    public static String input_charset = "utf-8";

    // 签名方式 不需修改
    public static String app_sign_type = "RSA";

    // 支付类型 ，无需修改
    public static String payment_type = "1";

    // PC 页面支付的服务 类型
    public static String app_pay_service = "mobile.securitypay.pay";

    //异步通知地址
    public static String notify_url = BASE_URL + "pay/aliPayReturn";

    //同步通知地址
    public static String return_url = BASE_URL + "pay/aliPaySyncReturn";


    //PC  支付参数
    // PC 页面支付的服务 类型
    //调用的接口名，无需修改
    public static String web_pay_service = "create_direct_pay_by_user";


    public final static String web_sign_type = "RSA";


    public static String web_apy_key = "";


}
