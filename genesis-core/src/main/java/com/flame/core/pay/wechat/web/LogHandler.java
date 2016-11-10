package com.flame.core.pay.wechat.web;

import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;

import java.util.Map;

/**
 * 微信日志
 * @修改人：xxx
 * @修改时间:xxxx-xx-xx
 * 
 */
public class LogHandler implements WxMpMessageHandler {
	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) {
		System.out.println(wxMessage.toString());
		return null;
	}
}
