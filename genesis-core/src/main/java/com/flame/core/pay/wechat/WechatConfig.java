package com.flame.core.pay.wechat;


import com.flame.core.pay.common.PayConfig;

/**
 * @ClassName: WechatConfig
 * @Description: TODO 微信相关配置
 * @date 2016年6月16日
 */
public class WechatConfig extends PayConfig {


    /**
     * 微信WEB，应用ID 在微信公众号--》基本配置
     */
    public static final String WEB_APP_ID = "wxc4300fb54189316b";
    /**
     * 微信WEB应用密钥  在微信公众号--》基本配置 只有公众号 开发用到
     */
    public static final String WEB_SECRET = "f32128c7b1790baf8157b8adf2f9faa1 ";
    /**
     * 微信WEB 应用 token 需要 checkApi去激活 否则URL  不被允许
     */
    public static final String WEB_TOKEN = "tokenaccesskey";

    /**
     * 微信WEB支付 商户ID
     */
    public static final String WEB_MCH_ID = "1329770601";

    /**
     * 微信WEB支付 授权地址
     */
    public static final String OAUTH_RETURN_URL = BASE_URL + "pay/wechatPayReturn";

    /**
     * 登录微信商户平台
     * 微信WEB 支付KEY
     */
    public static final String WEB_PAY_KEY = "b1d5d04950df450f80d32a52a568f558";


    /**
     * 微信WEB支付 异步通知地址
     */
    public static final String WEB_NOTIFY_URL = BASE_URL + "pay/wechatPayReturn";


    /***************************************
     * APP 配置开始/
     * <p>
     * <p>
     * /**
     * 微信APP，应用ID 申请的时候 在邮件里面
     */
    public static final String APP_APP_ID = "";

    /**
     * 微信APP支付 商户ID
     */
    public static final String APP_MCH_ID = "";

    /**
     * 微信APP 支付KEY
     */
    public static final String APP_PAY_KEY = "";


    /**
     * 微信APP支付 异步通知地址
     */
    public static final String APP_NOTIFY_URL = BASE_URL + "";


    /**
     * 微信支付 下单请求地址
     */
    public static final String WX_CREATE_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";


    /**
     * @author caogw
     * @ClassName: tradeType
     * @Description: TODO 交易类型
     * @date 2016年6月16日
     */
    public enum tradeType {
        JSAPI {
            public String getKey() {
                return WEB_PAY_KEY;
            }
        },
        NATIVE {
            public String getKey() {
                return WEB_PAY_KEY;
            }
        },
        APP {
            public String getKey() {
                return APP_PAY_KEY;
            }
        };

        public abstract String getKey();
    }

}
