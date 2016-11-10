package com.flame.core.pay.common;

import java.util.Map;

/**
 * 
    * @ClassName: Pay
    * @Description: TODO 支付 接口
    * @author caogw
    * @date 2016年6月18日
    *
 */
public interface Pay {

	/**
	 * 
	    * @Title: pay
	    * @Description: TODO 支付
	    * @param @param payParam
	    * @param @return
	    * @param @throws Exception    参数
	    * @return PayResult    返回类型
	    * @throws
	 */
	public PayResult pay(PayParam payParam) throws Exception;
	
	
	
	/**
	 * 
	    * @Title: payReturn
	    * @Description: TODO 支付完成后 回调 处理
	    * @param @param returnParam
	    * @param @return    参数
	    * @return boolean    返回类型
	    * @throws
	 */
	public boolean payReturn(Map<String, Object> returnParam);
}
