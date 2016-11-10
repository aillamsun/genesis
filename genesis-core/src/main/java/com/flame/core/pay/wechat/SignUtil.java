package com.flame.core.pay.wechat;

import com.flame.common.utils.security.MD5Utils;
import me.chanjar.weixin.common.util.RandomUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SignUtil {

    /**
     * @param @param  params
     * @param @param  key
     * @param @return 参数
     * @return String    返回类型
     * @throws
     * @Title: getSign
     * @Description: TODO 通过 集合获取 支付签名
     */
    public static String getSign(List<String> params, String key) {
        Collections.sort(params);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i) + "&");
        }
        sb.append("key=" + key);
        return MD5Utils.md5(sb.toString()).toUpperCase();

    }


    /**
     * @param @param  prepayId
     * @param @throws WxErrorException    参数
     * @return WechatPayResult    返回类型
     * @throws
     * @Title: createPaySignature
     * @Description: TODO  获取WEB支付的签名
     */
    public static WechatPayResult createWebPaySign(String prepayId) {
        prepayId = "prepay_id=" + prepayId;
        long timestamp = System.currentTimeMillis() / 1000;
        String noncestr = RandomUtils.getRandomStr();
        String signType = "MD5";
        List<String> params = new ArrayList<String>();
        params.add("appId=" + WechatConfig.WEB_APP_ID);
        params.add("nonceStr="
                + noncestr);
        params.add("timeStamp=" + timestamp);
        params.add("package=" + prepayId);
        params.add("signType=" + signType);
        String signature = getSign(params, WechatConfig.WEB_PAY_KEY);


        WechatPayResult paySignResult = new WechatPayResult();
        paySignResult.setAppId(WechatConfig.WEB_APP_ID);
        paySignResult.setNoncestr(noncestr);
        paySignResult.setSignType(signType);
        paySignResult.setPrepayId(prepayId);
        paySignResult.setTimestamp(timestamp);
        paySignResult.setSignature(signature);
        return paySignResult;
    }


    /**
     * @param @param  prepayId
     * @param @return
     * @param @throws WxErrorException    参数
     * @return WechatPayResult    返回类型
     * @throws
     * @Title: createAppPaySignature
     * @Description: TODO 获取 APP 支付签名
     */
    public static WechatPayResult createAppPaySignature(String prepayId) {
        long timestamp = System.currentTimeMillis() / 1000;
        String noncestr = RandomUtils.getRandomStr();
        String signType = "MD5";

        List<String> params = new ArrayList<String>();
        params.add("appid=" + WechatConfig.APP_APP_ID);
        params.add("noncestr="
                + noncestr);
        params.add("timestamp=" + timestamp);
        params.add("package=Sign=WXPay");
        params.add("prepayid=" + prepayId);
        params.add("partnerid=" + WechatConfig.APP_MCH_ID);

        String signature = getSign(params, WechatConfig.APP_PAY_KEY);

        WechatPayResult paySignResult = new WechatPayResult();
        paySignResult.setAppId(WechatConfig.APP_PAY_KEY);
        paySignResult.setNoncestr(noncestr);
        paySignResult.setSignType(signType);
        paySignResult.setPrepayId(prepayId);
        paySignResult.setTimestamp(timestamp);
        paySignResult.setSignature(signature);
        paySignResult.setPartnerId(WechatConfig.APP_MCH_ID);
        return paySignResult;
    }


}
