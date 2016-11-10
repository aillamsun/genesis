package com.flame.core.pay.wechat.web;

import com.flame.core.pay.wechat.WechatConfig;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;


/**
 * @author caogw
 * @ClassName: WxMpServiceFactory
 * @Description: TODO 微信公众号 初始化工厂
 * @date 2016年6月16日
 */
public class WxMpServiceFactory {

    private static WxMpService wxMpService;

    private static WxMpInMemoryConfigStorage config;

    /**
     * 获取微信配置对象
     *
     * @return
     */
    public static WxMpService getWxMpService() {
        if (wxMpService == null) {
            initService();
        }
        return wxMpService;
    }

    /**
     * 获取配置
     *
     * @return
     */
    public static WxMpInMemoryConfigStorage getConfig() {
        if (config == null) {
            initConfig();
        }
        return config;
    }

    /**
     * 初始化微信相关配置
     */
    public static void initConfig() {
        config = new WxMpInMemoryConfigStorage();
        // 设置微信公众号的appid
        config.setAppId(WechatConfig.WEB_APP_ID);
        // 设置微信公众号的appsecret
        config.setSecret(WechatConfig.WEB_SECRET);
        // 设置微信公众号的token
        config.setToken(WechatConfig.WEB_TOKEN);
        // 网页授权获取用户信息回调地址
        config.setOauth2redirectUri(WechatConfig.OAUTH_RETURN_URL);
    }

    /**
     * 初始化微信相关配置
     */
    public static void initService() {
        wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(getConfig());
    }

}
