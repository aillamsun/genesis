package com.flame.core.pay.alipay;

import com.flame.core.pay.alipay.common.AlipaySubmit;
import com.flame.core.pay.alipay.common.RSA;
import com.flame.core.pay.common.Pay;
import com.flame.core.pay.common.PayParam;
import com.flame.core.pay.common.PayResult;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


public class AliPay implements Pay {

    @Override
    public PayResult pay(PayParam payParam) throws Exception {
        if (payParam.getPayPlatType().equals(PayParam.ALIPAY_APP)) {
            return appPay(payParam);
        } else if (payParam.getPayPlatType().equals(PayParam.ALIPAY_PC_WEB)) {
            return webPCPay(payParam);
        } else {
            return webPhonePay(payParam);
        }
    }


    /**
     * @param @param  payParam
     * @param @return 参数
     * @return AlipayPayResult    返回类型
     * @throws
     * @Title: appPay
     * @Description: TODO 支付宝APP 支付
     */
    public AlipayPayResult appPay(PayParam payParam) {
        AlipayPayResult aliPayResult = new AlipayPayResult();

        aliPayResult.set_input_charset(AlipayConfig.input_charset);
        aliPayResult.setBody(payParam.getPayInfo());
        aliPayResult.setIt_b_pay("30m");
        aliPayResult.setNotify_url(payParam.getNotifyUrl());
        aliPayResult.setOut_trade_no(payParam.getPayOrderId());
        aliPayResult.setPartner(AlipayConfig.partner);
        aliPayResult.setPayment_type("1");
        aliPayResult.setSeller_id(AlipayConfig.partner);
        aliPayResult.setService(AlipayConfig.app_pay_service);
//		aliPayResult.setShow_url("");
        aliPayResult.setSign_type(AlipayConfig.app_sign_type);
        aliPayResult.setSubject(payParam.getPayInfo());
        aliPayResult.setTotal_fee(payParam.getPayAmount());

        //需要签名的字符
        String signContent = "partner=\"" + aliPayResult.getPartner() + "\"&seller_id=\"" + aliPayResult.getSeller_id() + "\"&out_trade_no=\"" + aliPayResult.getOut_trade_no()
                + "\"&subject=\"" + aliPayResult.getSubject() + "\"&body=\"" + aliPayResult.getBody() + "\"&total_fee=\"" + aliPayResult.getTotal_fee() + "\"&notify_url=\""
                + aliPayResult.getNotify_url() + "\"&service=\"" + aliPayResult.getService()
                + "\"&payment_type=\"" + aliPayResult.getPayment_type() + "\"&_input_charset=\"" + aliPayResult.get_input_charset() + "\"&it_b_pay=\"" + aliPayResult.getIt_b_pay() + "\"";

        String sign = RSA.sign(signContent, AlipayConfig.private_key, AlipayConfig.input_charset);
        try {
            sign = URLEncoder.encode(sign, AlipayConfig.input_charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        aliPayResult.setSign(sign);
        String requestParam = signContent + "&sign=\"" + sign + "\"&sign_type=\"" + aliPayResult.getSign_type() + "\"";
        aliPayResult.setRequestInfo(requestParam);
        aliPayResult.setRequestParam(requestParam);
        return aliPayResult;
    }


    /**
     * @param @param  payParam
     * @param @return 参数
     * @return AlipayPayResult    返回类型
     * @throws
     * @Title: webPCPay
     * @Description: TODO 支付宝 PC 网页支付
     */
    public AlipayPayResult webPCPay(PayParam payParam) {
        AlipayPayResult aliPayResult = new AlipayPayResult();
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", AlipayConfig.web_pay_service);
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_id", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("payment_type", "1");
        sParaTemp.put("notify_url", AlipayConfig.notify_url);
        sParaTemp.put("return_url", AlipayConfig.return_url);
//		sParaTemp.put("anti_phishing_key", AlipayConfig.anti_phishing_key);
//		sParaTemp.put("exter_invoke_ip", AlipayConfig.exter_invoke_ip);
        sParaTemp.put("out_trade_no", payParam.getPayOrderId());
        sParaTemp.put("subject", payParam.getPayInfo());
        sParaTemp.put("total_fee", payParam.getPayAmount() + "");
        sParaTemp.put("body", payParam.getPayInfo());

        aliPayResult.setStatus(1);
        aliPayResult.setRequestInfo(sParaTemp.toString());
        aliPayResult.setReturnInfo(AlipaySubmit.buildRequest(sParaTemp, "POST", "确认"));
        return aliPayResult;
    }

    /**
     * @param @param  payParam
     * @param @return 参数
     * @return AlipayPayResult    返回类型
     * @throws
     * @Title: webPhonePay
     * @Description: TODO 微信手机端网页支付
     */
    public AlipayPayResult webPhonePay(PayParam payParam) {
        AlipayPayResult aliPayResult = new AlipayPayResult();
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", AlipayConfig.web_pay_service);
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_id", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("payment_type", "1");
        sParaTemp.put("notify_url", payParam.getNotifyUrl());
        sParaTemp.put("return_url", AlipayConfig.return_url);
        sParaTemp.put("out_trade_no", payParam.getPayOrderId());
        sParaTemp.put("subject", payParam.getPayInfo());
        sParaTemp.put("total_fee", payParam.getPayAmount() + "");
        sParaTemp.put("body", payParam.getPayInfo());

        aliPayResult.setStatus(1);
        aliPayResult.setRequestInfo(sParaTemp.toString());
        aliPayResult.setReturnInfo(AlipaySubmit.buildRequest(sParaTemp, "get", ""));
        return aliPayResult;
    }


    @Override
    public boolean payReturn(Map<String, Object> returnParam) {
        return false;
    }
}
