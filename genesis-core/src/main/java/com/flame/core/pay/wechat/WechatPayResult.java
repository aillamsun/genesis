package com.flame.core.pay.wechat;


import com.flame.core.pay.common.PayResult;

/**
 * @author caogw
 * @ClassName: WechatPayResult
 * @Description: TODO  初始化支付 返回的响应数据
 * @date 2016年6月16日
 */
public class WechatPayResult extends PayResult {

    /**
     * 此处的 签名返回 微信手机端支付会用的到
     */
    private String appId;
    private String noncestr;
    private long timestamp;
    private String prepayId;
    private String signType;
    private String signature;
    private String orderId;
    private Boolean useBalance;
    private String partnerId;
    private String codeUrl;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Boolean getUseBalance() {
        return useBalance;
    }

    public void setUseBalance(Boolean useBalance) {
        this.useBalance = useBalance;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }


}
