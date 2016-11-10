package com.flame.core.pay.union;

import com.flame.common.utils.Arith;
import com.flame.core.pay.common.Pay;
import com.flame.core.pay.common.PayParam;
import com.flame.core.pay.common.PayResult;
import com.flame.core.pay.union.common.AcpService;
import com.flame.core.pay.union.common.SDKConfig;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UnionPay implements Pay {

    @Override
    public PayResult pay(PayParam payParam) throws Exception {
        if (payParam.getPayPlatType().equals(PayParam.UNION_PC_WEB)) {
            return webPCPay(payParam);
        }
        return null;
    }


    /**
     * @param @param  payParam
     * @param @return 参数
     * @return UnionPayResult    返回类型
     * @throws
     * @Title: webPCPay
     * @Description: TODO 银联 PC 端支付
     */
    public UnionPayResult webPCPay(PayParam payParam) {
        UnionPayResult unionPaySignResult = new UnionPayResult();

        Map<String, String> requestData = new HashMap<String, String>();

        //版本号，全渠道默认值
        requestData.put("version", UnionpayConfig.version);
        //字符集编码，可以使用UTF-8,GBK两种方式
        requestData.put("encoding", UnionpayConfig.encoding);
        //签名方法，只支持 01：RSA方式证书加密
        requestData.put("signMethod", "01");
        //交易类型 ，01：消费
        requestData.put("txnType", "01");
        //交易子类型， 01：自助消费
        requestData.put("txnSubType", "01");
        //业务类型，B2C网关支付，手机wap支付
        requestData.put("bizType", "000201");
        //渠道类型，这个字段区分B2C网关支付和手机wap支付；07：PC,平板  08：手机
        requestData.put("channelType", "07");
        //商户号码
        requestData.put("merId", UnionpayConfig.merId);
        //接入类型，0：直连商户
        requestData.put("accessType", "0");
        //商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则
        requestData.put("orderId", payParam.getPayOrderId());
        //订单发送时间，取系统时间，格式为YYYYMMDDhhmmss，必须取当前时间，否则会报txnTime无效
        requestData.put("txnTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        //交易币种（境内商户一般是156 人民币）
        requestData.put("currencyCode", "156");
        //交易金额，单位分，不要带小数点
        requestData.put("txnAmt", Double.valueOf(Arith.mul(Double.valueOf(payParam.getPayAmount()), 100)).intValue() + "");
        requestData.put("frontUrl", UnionpayConfig.frontUrl);
        requestData.put("backUrl", payParam.getNotifyUrl());
        //签名
        Map<String, String> submitFromData = AcpService.sign(requestData, UnionpayConfig.encoding);

        String requestFrontUrl = SDKConfig.getConfig().getFrontRequestUrl();
        String html = AcpService.createAutoFormHtml(requestFrontUrl, submitFromData, UnionpayConfig.encoding);

        unionPaySignResult.setStatus(1);
        unionPaySignResult.setRequestInfo(requestData.toString());
        unionPaySignResult.setReturnInfo(html);
        return unionPaySignResult;
    }


    @Override
    public boolean payReturn(Map<String, Object> returnParam) {
        return false;
    }

}
