package com.flame.core.pay.wechat.web;

import com.flame.core.pay.wechat.WechatConfig;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信公众号开发 使用
 *
 * @author caogw
 * @ClassName: WechatWebUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2016年6月16日
 */
public class WechatWebUtil {


    /**
     * @param @param inRequest
     * @param @param inResponse    参数
     * @return void    返回类型
     * @throws
     * @Title: checkApi
     * @Description: TODO 第一次配置微信公众号时激活TOKEN 使用
     */
    public void checkApi(HttpServletRequest inRequest, HttpServletResponse inResponse) {
        try {
            WxMpMessageHandler logHandler = new LogHandler();
            WxMpMessageRouter wxMpMessageRouter = new WxMpMessageRouter(WxMpServiceFactory.getWxMpService());
            wxMpMessageRouter.rule().handler(logHandler).end();
            inResponse.setContentType("text/html;charset=utf-8");
            inResponse.setStatus(HttpServletResponse.SC_OK);
            String signature = inRequest.getParameter("signature");
            String nonce = inRequest.getParameter("nonce");
            String timestamp = inRequest.getParameter("timestamp");
            if (!WxMpServiceFactory.getWxMpService().checkSignature(timestamp, nonce, signature)) {
                // 消息签名不正确，说明不是公众平台发过来的消息
                inResponse.getWriter().println("非法请求");
                return;
            }
            String echostr = inRequest.getParameter("echostr");
            if (StringUtils.isNotBlank(echostr)) {
                // 说明是一个仅仅用来验证的请求，回显echostr
                inResponse.getWriter().println(echostr);
                return;
            }
            String encryptType = StringUtils.isBlank(inRequest.getParameter("encrypt_type")) ? "raw" : inRequest.getParameter("encrypt_type");
            if ("raw".equals(encryptType)) {
                // 明文传输的消息
                WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(inRequest.getInputStream());
                WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
                if (outMessage != null) {
                    inResponse.getWriter().write(outMessage.toXml());
                }
                return;
            }
            if ("aes".equals(encryptType)) {
                // 是aes加密的消息
                String msgSignature = inRequest.getParameter("msg_signature");
                WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(inRequest.getInputStream(), WxMpServiceFactory.getConfig(), timestamp, nonce, msgSignature);
                WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
                inResponse.getWriter().write(outMessage.toEncryptedXml(WxMpServiceFactory.getConfig()));
                return;
            }
            inResponse.getWriter().println("不可识别的加密类型");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @return Map<String, Object>    返回类型
     * @throws WxErrorException
     * @throws
     * @Title: signatureUrl
     * @Description: TODO  对微信WEB 访问的URL 进行签名 否则无法使用 JSSDK
     */
    public static Map<String, Object> signatureUrl(String signurl) throws WxErrorException {
        Map<String, Object> shareConfig = new HashMap<String, Object>();
        WxJsapiSignature wxJsapiSignature = null;
        wxJsapiSignature = WxMpServiceFactory.getWxMpService().createJsapiSignature(signurl);
        if (wxJsapiSignature == null) {
            return shareConfig;
        }
        shareConfig.put("timestamp", wxJsapiSignature.getTimestamp());
        shareConfig.put("nonceStr", wxJsapiSignature.getNoncestr());
        shareConfig.put("signature", wxJsapiSignature.getSignature());
        shareConfig.put("appId", WechatConfig.WEB_APP_ID);
        shareConfig.put("url", signurl);
        return shareConfig;
    }


    /**
     * @param @param  oauthType
     * @param @param  paramData
     * @param @return 参数
     * @return String    返回类型
     * @throws UnsupportedEncodingException
     * @throws
     * @Title: oauth2
     * @Description: TODO   获取授权的 URL ，用来重定向到微信的授权接口
     */
    public static String oauth2(String oauthType, String paramData, String oauthRtnSuccUrl) throws UnsupportedEncodingException {
        WxMpService wxMpService = WxMpServiceFactory.getWxMpService();
        //如果没有传入 则取 当时 配置的 授权地址
        if (StringUtils.isBlank(oauthRtnSuccUrl)) {
            oauthRtnSuccUrl = WxMpServiceFactory.getConfig().getOauth2redirectUri();
        }
        return wxMpService.oauth2buildAuthorizationUrl(
                oauthRtnSuccUrl, StringUtils.isNotBlank(oauthType) ? oauthType : WxConsts.OAUTH2_SCOPE_BASE, URLEncoder.encode(paramData, "utf-8"));
    }


    /**
     * @param @param    code  授权换取的code
     * @param isDetails 是否是获取了 详细信息
     * @param @return   参数
     * @return Map<String,Object>    返回类型
     * @throws WxErrorException
     * @throws
     * @Title: oauthReturn
     * @Description: TODO  授权成功后   获取 授权用户的信息
     */
    public static Map<String, Object> oauthReturn(String code, boolean isDetails) throws WxErrorException {
        Map<String, Object> userInfo = new HashMap<String, Object>();
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = null;
        wxMpOAuth2AccessToken = WxMpServiceFactory.getWxMpService().oauth2getAccessToken(code);
        if (wxMpOAuth2AccessToken == null) {
            return userInfo;
        }
        userInfo.put("openId", wxMpOAuth2AccessToken.getOpenId());
        if (isDetails) {
            WxMpUser wxMpUser = WxMpServiceFactory.getWxMpService().oauth2getUserInfo(wxMpOAuth2AccessToken, null);
            if (wxMpUser != null) {
                userInfo.put("detailsInfo", wxMpUser);
            }
        }
        return userInfo;
    }

    /**
     * @param @param  longUrl
     * @param @return
     * @param @throws WxErrorException    参数
     * @return String    返回类型
     * @throws
     * @Title: getShortUrl
     * @Description: TODO 获取短链接
     */
    public static String getShortUrl(String longUrl) {
        try {
            return WxMpServiceFactory.getWxMpService().shortUrl(longUrl);
        } catch (WxErrorException e) {
            e.printStackTrace();
            return "";
        }
    }


}
