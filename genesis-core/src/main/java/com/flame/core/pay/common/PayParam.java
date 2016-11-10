package com.flame.core.pay.common;

public class PayParam {

    /**
     * 支付金额
     */
    private Double payAmount;

    /**
     * 支付单ID
     */
    private String payOrderId;

    /**
     * 支付信息
     */
    private String payInfo;

    /**
     * 微信公众号支付时的openId
     */
    private String openId;

    /**
     * 异步通知地址
     */
    private String notifyUrl;

    /**
     * @ClassName: tradePlatType
     * @Description: TODO 交易平台类型
     * @author caogw
     * @date 2016年6月18日
     */
    private String payPlatType;

    public final static String WX_JSAPI = "WX_JSAPI"; // 微信官网
    public final static String WX_NATIVE = "WX_NATIVE"; //微信PC
    public final static String WX_APP = "WX_APP"; //微信APP
    public final static String ALIPAY_APP = "ALIPAY_APP"; //支付宝APP
    public final static String ALIPAY_PC_WEB = "ALIPAY_PC_WEB"; //支付宝PC
    public final static String ALIPAY_PHONE_WEB = "ALIPAY_PHONE_WEB"; //支付宝 手机页面
    public final static String UNION_APP = "UNION_APP"; //银联 APP
    public final static String UNION_PC_WEB = "UNION_PC_WEB"; //银联PC网页
    public final static String UNION_PHONE_WEB = "UNION_PHONE_WEB"; //银联手机网页


    public PayParam() {

    }


    public PayParam(String payPlatType, Double payAmount, String payOrderId, String payInfo) {
        this.payPlatType = payPlatType;
        this.payAmount = payAmount;
        this.payOrderId = payOrderId;
        this.payInfo = payInfo;
    }


    public Double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(String payOrderId) {
        this.payOrderId = payOrderId;
    }

    public String getPayInfo() {
        return payInfo;
    }

    public void setPayInfo(String payInfo) {
        this.payInfo = payInfo;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }


    public String getPayPlatType() {
        return payPlatType;
    }


    public void setPayPlatType(String payPlatType) {
        this.payPlatType = payPlatType;
    }


    public String getNotifyUrl() {
        return notifyUrl;
    }


    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }


}

 


