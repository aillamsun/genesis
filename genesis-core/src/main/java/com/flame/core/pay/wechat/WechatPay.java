package com.flame.core.pay.wechat;

import com.flame.common.utils.http.HttpClientUtils;
import com.flame.core.pay.common.Pay;
import com.flame.core.pay.common.PayParam;
import com.flame.core.pay.common.PayResult;
import com.flame.core.pay.common.PayUtils;
import me.chanjar.weixin.common.util.RandomUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.*;
import java.util.Map.Entry;


/**
 * 
    * @ClassName: WechatPay
    * @Description: TODO  微信支付
    * @author caogw
    * @date 2016年6月16日
    *
 */
public class WechatPay implements Pay{
 
 
	@Override
	public PayResult pay(PayParam payParam) throws Exception {
		if(PayParam.WX_JSAPI.equals(payParam.getPayPlatType())){
			return webPhonePay(payParam);
		}else if(PayParam.WX_APP.equals(payParam.getPayPlatType())){
			return appPay(payParam);
		}else{
			return webPCPay(payParam);
		}
	
	}
	


	/**
	 * 
	    * @Title: webPay
	    * @Description: TODO 微信公众号支付
	    * @param @param payParam
	    * @param @return    参数
	    * @return WechatPayResult    返回类型
	    * @throws
	 */
	public  WechatPayResult webPhonePay(PayParam payParam){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("appid", WechatConfig.WEB_APP_ID);
		paramMap.put("body", payParam.getPayInfo());
		paramMap.put("input_charset", "UTF-8");
		paramMap.put("mch_id", WechatConfig.WEB_MCH_ID);
		paramMap.put("nonce_str", RandomUtils.getRandomStr());
		paramMap.put("notify_url", payParam.getNotifyUrl());
		paramMap.put("out_trade_no", payParam.getPayOrderId());
		paramMap.put("spbill_create_ip", "127.0.0.1");
		paramMap.put("openId", payParam.getOpenId());
		paramMap.put("total_fee", PayUtils.YuanToFen(payParam.getPayAmount())
				+ "");
		paramMap.put("trade_type", WechatConfig.tradeType.JSAPI.toString());
		//下单 并获取支付响应的参数
		WechatPayResult payResult = createOrder(paramMap,WechatConfig.tradeType.JSAPI);
		return payResult;
	}
	
	
	/**
	 * 
	    * @Title: webPCPay
	    * @Description: TODO  微信 PC 本地支付
	    * @param @param payParam
	    * @param @return    参数
	    * @return WechatPayResult    返回类型
	    * @throws
	 */
	public  WechatPayResult webPCPay(PayParam payParam){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("appid", WechatConfig.WEB_APP_ID);
		paramMap.put("body", payParam.getPayInfo());
		paramMap.put("input_charset", "UTF-8");
		paramMap.put("mch_id", WechatConfig.WEB_MCH_ID);
		paramMap.put("nonce_str", RandomUtils.getRandomStr());
		paramMap.put("notify_url",WechatConfig.WEB_NOTIFY_URL);
		paramMap.put("out_trade_no", payParam.getPayOrderId());
		paramMap.put("spbill_create_ip", "127.0.0.1");
		paramMap.put("total_fee", PayUtils.YuanToFen(payParam.getPayAmount())
				+ "");
		paramMap.put("trade_type", WechatConfig.tradeType.NATIVE.toString());
		//下单 并获取支付响应的参数
		WechatPayResult payResult = createOrder(paramMap,WechatConfig.tradeType.NATIVE);
		return payResult;
	}
	
	
	/**
	 * 
	    * @Title: appPay
	    * @Description: TODO 微信APP 支付
	    * @param @param payParam
	    * @param @return    参数
	    * @return WechatPayResult    返回类型
	    * @throws
	 */
	public  WechatPayResult appPay(PayParam payParam){
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("appid", WechatConfig.APP_APP_ID);
		paramMap.put("body", payParam.getPayInfo());
		paramMap.put("input_charset", "UTF-8");
		paramMap.put("mch_id", WechatConfig.APP_MCH_ID);
		paramMap.put("nonce_str", RandomUtils.getRandomStr());
		paramMap.put("notify_url", WechatConfig.APP_NOTIFY_URL);
		paramMap.put("out_trade_no", payParam.getPayOrderId());
		paramMap.put("spbill_create_ip", "127.0.0.1");
		paramMap.put("total_fee", PayUtils.YuanToFen(payParam.getPayAmount())
				+ "");
		paramMap.put("trade_type", WechatConfig.tradeType.APP.toString());
		//下单 并获取支付响应的参数
		WechatPayResult payResult = createOrder(paramMap,WechatConfig.tradeType.APP);
		return payResult;
	}
	

	/**
	 * 
	    * @Title: createOrder
	    * @Description: TODO  微信 下单
	    * @param @param orderMap
	    * @param @param key
	    * @param @return    参数
	    * @return WechatPayResult    返回类型
	    * @throws
	 */
	private static WechatPayResult createOrder(Map<String, String> orderMap, WechatConfig.tradeType tradeType){
		// 生成XML数据
		List<String> orderParams = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");

		Iterator<Entry<String, String>> it = orderMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> map = (Entry<String, String>) it
					.next();
			// sb2是用来做请求的xml参数
			sb.append("<" + map.getKey() + ">");
			sb.append(map.getValue());
			sb.append("</" + map.getKey() + ">");
			orderParams.add(map.getKey() + "=" + map.getValue());
		}

		sb.append("<sign>");
		sb.append(SignUtil.getSign(orderParams,tradeType.getKey()));
		sb.append("</sign>");

		sb.append("</xml>");
		String xmlStr = "";
		try {
			xmlStr = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//发送下单请求
		String result = HttpClientUtils.getSendBody(WechatConfig.WX_CREATE_ORDER_URL, xmlStr, "utf-8");
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(result);
		} catch (DocumentException e) {
			e.printStackTrace();

		}
		Element rootElt = doc.getRootElement(); // 获取根节
		String return_code = rootElt.element("return_code").getTextTrim(); // 是否成功
		//解析出商品ID
		String prepayId = "";
		WechatPayResult payResult = new WechatPayResult();
		
		if ("SUCCESS".equals(return_code)) {
			prepayId = rootElt.element("prepay_id").getTextTrim(); // 商品ID
			//微信公众号支付和APP 支付 需要获取签名
			if(tradeType==WechatConfig.tradeType.APP||tradeType==WechatConfig.tradeType.JSAPI){
				payResult = SignUtil.createWebPaySign(prepayId);
			}else{
				//支付二维码
				String codeUrl = rootElt.element("code_url")==null?"": rootElt.element("code_url").getTextTrim();
				payResult.setCodeUrl(codeUrl);
			}
			payResult.setStatus(1);
		}else{
			payResult.setStatus(0);
		}
		//请求和响应的数据
		payResult.setRequestInfo(xmlStr);
		payResult.setReturnInfo(result);
		return payResult;
	}



	@Override
	public boolean payReturn(Map<String, Object> returnParam) {
		return false;
	}
 
}
